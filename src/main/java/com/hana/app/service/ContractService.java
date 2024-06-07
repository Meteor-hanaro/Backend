package com.hana.app.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hana.app.data.entity.Contract;
import com.hana.app.data.entity.Pb;
import com.hana.app.data.entity.VIP;
import com.hana.app.data.entity.fund.Fund;
import com.hana.app.data.entity.portfolio.Portfolio;
import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.data.entity.suggestion.SuggestionItem;
import com.hana.app.repository.ContractRepository;
import com.hana.app.service.fund.FundService;
import com.hana.app.service.user.VipService;
import com.hana.dto.request.ContractRequestDto;
import com.hana.dto.request.FinalContractRequestDto;
import com.hana.exception.InternalServerException;
import com.hana.exception.NotFoundException;
import com.hana.external.aws.S3Service;
import com.hana.response.ErrorType;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ContractService {

	private final ContractRepository contractRepository;
	private final SuggestionService suggestionService;
	private final PortfolioService portfolioService;
	private final VipService vipService;
	private final PbService pbService;
	private final FundService fundService;
	private final S3Service s3Service;

	@Value("${pdf.secret}")
	private String key;

	@Transactional
	public void completeContracts(FinalContractRequestDto finalContractRequestDto) {

		Long vipId = finalContractRequestDto.getVipId();
		VIP vip = vipService.getVipByVipId(vipId);
		Pb pb = pbService.findByPbId(finalContractRequestDto.getPbId());
		Portfolio portfolio = portfolioService.findByPortfolioByVipId(vipId);
		Long suggestionId = finalContractRequestDto.getSuggestionId();

		makeContracts(pb, vip, finalContractRequestDto);

		portfolioService.makePortfolioItemInactive(portfolio.getId());

		moveSuggestionToPortfolio(suggestionId, portfolio);

		suggestionService.deleteAllSuggestion(portfolio);
	}

	private void makeContracts(Pb pb, VIP vip, FinalContractRequestDto finalContractRequestDto) {

		for (ContractRequestDto contractRequestDto : finalContractRequestDto.getContracts()) {
			Fund fund = fundService.get(contractRequestDto.getFundId()).
				orElseThrow(() -> new NotFoundException(ErrorType.NOT_FOUND));

			byte[] decodedBytes = Base64.getDecoder().decode(contractRequestDto.getSignedContract());
			byte[] encryptedBytes = encrypt(decodedBytes);

			String signedContractPdfUrl = s3Service.uploadPdfInByte(encryptedBytes, "signed");

			Contract contract = Contract.builder()
				.signedContract(signedContractPdfUrl)
				.pb(pb)
				.vip(vip)
				.fund(fund)
				.build();

			contractRepository.save(contract);
		}
	}

	private void moveSuggestionToPortfolio(Long suggestionId, Portfolio portfolio) {
		List<SuggestionItem> suggestionItems = suggestionService.getSuggestionItems(suggestionId);
		List<PortfolioItem> portfolioItems = suggestionItems.stream()
			.map(suggestionItem -> PortfolioItem.toPortfolioItem(portfolio, suggestionItem)).toList();

		portfolioService.savePortfolioItems(portfolioItems);
	}

	private byte[] encrypt(byte[] data) {
		try {
			SecretKeySpec secretKey = generateKey(key);
			Cipher cipher = Cipher.getInstance("AES");
			cipher.init(Cipher.ENCRYPT_MODE, secretKey);
			return cipher.doFinal(data);
		} catch (Exception e) {
			System.out.println(e.getMessage());
			throw new InternalServerException(ErrorType.INTERNAL_SERVER);
		}
	}

	private SecretKeySpec generateKey(String key) throws Exception {
		MessageDigest sha = MessageDigest.getInstance("SHA-256");
		byte[] keyBytes = key.getBytes(StandardCharsets.UTF_8);
		keyBytes = sha.digest(keyBytes);
		return new SecretKeySpec(Arrays.copyOf(keyBytes, 16), "AES");
	}
}

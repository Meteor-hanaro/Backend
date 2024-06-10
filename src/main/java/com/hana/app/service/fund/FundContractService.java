package com.hana.app.service.fund;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hana.app.data.entity.fund.Fund;
import com.hana.app.data.entity.fund.FundContract;
import com.hana.app.repository.fund.FundContractRepository;
import com.hana.app.repository.fund.FundRepository;
import com.hana.dto.response.fund.FundContractDto;
import com.hana.dto.response.fund.FundContractsResponseDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FundContractService {

	private final FundContractRepository fundContractRepository;
	private final FundRepository fundRepository;

	public List<FundContractsResponseDto> getFundJoinContractsByFundIds(List<Long> fundIds) {
		List<Fund> funds = fundRepository.findByIdIn(fundIds);
		List<FundContract> fundContracts = fundContractRepository.findByFundIdIn(fundIds);

		return funds.stream().map(fund -> {
			List<FundContractDto> fundContractDtos = fundContracts.stream()
				.filter(contract -> contract.getFund().getId().equals(fund.getId()))
				.filter(fundContract -> fundContract.getContractType().equals(FundContract.ContractType.JOIN))
				.map(FundContractDto::from)
				.collect(Collectors.toList());

			return FundContractsResponseDto.from(fund, fundContractDtos);
		}).collect(Collectors.toList());
	}
}

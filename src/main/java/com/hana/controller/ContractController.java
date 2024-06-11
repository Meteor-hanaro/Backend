package com.hana.controller;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hana.app.service.fund.FundContractService;
import com.hana.app.service.ContractService;
import com.hana.dto.request.FinalContractRequestDto;
import com.hana.dto.response.fund.FundContractsResponseDto;
import com.hana.external.aws.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contract")
@RequiredArgsConstructor
@Tag(name = "Contract", description = "Response Contract API")
public class ContractController {

	private final S3Service s3Service;
	private final FundContractService fundContractService;
	private final ContractService contractService;

	@PostMapping("/upload")
	public String uploadPdf(@RequestParam("file") MultipartFile file) {
		return s3Service.uploadPdf(file, "contract");
	}

	@PostMapping("/join")
	public List<FundContractsResponseDto> getFundContractsByIds(@RequestBody Map<String, List<Long>> request) {
		List<Long> fundIds = request.get("fundIds");
		return fundContractService.getFundJoinContractsByFundIds(fundIds);
	}
	@PostMapping("/finalcontract")
	public List<FundContractsResponseDto> getFinalFundContractsByIds(@RequestBody Map<String, List<Long>> request) {
		List<Long> fundIds = request.get("fundIds");
		return fundContractService.getFundFinalContractsByFundIds(fundIds);
	}
	@PostMapping()
	public void completeContracts(@RequestBody FinalContractRequestDto finalContractRequestDto) {
		contractService.completeContracts(finalContractRequestDto);
	}

}

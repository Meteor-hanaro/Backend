package com.hana.controller;

import java.util.List;
import java.util.Map;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hana.app.service.FundContractService;
import com.hana.dto.response.FundContractDto;
import com.hana.dto.response.FundContractsResponseDto;
import com.hana.external.aws.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contract")
@RequiredArgsConstructor
@Slf4j
@Tag(name = "Contract", description = "Response Contract API")
public class ContractController {

	private final S3Service s3Service;
	private final FundContractService fundContractService;

	@PostMapping("/upload")
	public String uploadPdf(@RequestParam("file") MultipartFile file) {
		return s3Service.uploadPdf(file, "contract");
	}

	@PostMapping("/join")
	public List<FundContractsResponseDto> getFundContractsByIds(@RequestBody Map<String, List<Long>> request) {
		List<Long> fundIds = request.get("fundIds");
		return fundContractService.getFundJoinContractsByFundIds(fundIds);
	}

// FINAL 계약서 가져오는 코드
	@PostMapping("/finalcontract")
	public List<FundContractsResponseDto> getFinalFundContractsByIds(@RequestBody Map<String, List<Long>> request) {
		List<Long> fundIds = request.get("fundIds");
		return fundContractService.getFundFinalContractsByFundIds(fundIds);
	}

}

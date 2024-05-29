package com.hana.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hana.app.service.FundContractService;
import com.hana.dto.response.FundContractDto;
import com.hana.external.aws.S3Service;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contract")
@RequiredArgsConstructor
public class ContractController {

	private final S3Service s3Service;
	private final FundContractService fundContractService;

	@PostMapping("/upload")
	public String uploadPdf(@RequestParam("file") MultipartFile file) {
		return s3Service.uploadPdf(file, "contract");
	}

	@GetMapping("/join/{fundId}")
	public List<FundContractDto> getContracts(
		@PathVariable("fundId") final Long fundId)
	{
		return fundContractService.getFundJoinContractsByFundId(fundId);
	}

}

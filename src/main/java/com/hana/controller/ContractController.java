package com.hana.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.hana.external.aws.S3Service;
import com.hana.response.MeteorResponse;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/contract")
@RequiredArgsConstructor
public class ContractController {

	private final S3Service s3Service;

	@PostMapping("/upload")
	public MeteorResponse.MeteorSuccessResponse<String> uploadPdf(@RequestParam("file") MultipartFile file) {
		String fileUrl = s3Service.uploadPdf(file, "contract");
		return MeteorResponse.success(fileUrl);
	}

}

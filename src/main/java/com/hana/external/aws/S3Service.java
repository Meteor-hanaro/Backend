package com.hana.external.aws;

import static com.hana.response.ErrorType.*;

import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.hana.exception.InternalServerException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class S3Service {
	private final AmazonS3 amazonS3;

	@Value("${cloud.aws.s3.bucket}")
	private String bucket;

	public String uploadPdf(MultipartFile multipartFile, String folder) {
		String fileName = UUID.randomUUID().toString() + ".pdf";
		ObjectMetadata metadata = new ObjectMetadata();
		metadata.setContentType("application/pdf");
		metadata.setContentLength(multipartFile.getSize());

		try(InputStream inputStream = multipartFile.getInputStream()) {
			amazonS3.putObject(new PutObjectRequest(bucket+"/"+ folder + "/pdf", fileName, inputStream, metadata));
			return amazonS3.getUrl(bucket+"/"+ folder + "/pdf", fileName).toString();
		} catch(IOException e) {
			throw new InternalServerException(INTERNAL_SERVER);
		}
	}
}


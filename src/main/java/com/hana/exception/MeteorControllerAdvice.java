package com.hana.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hana.response.MeteorResponse;

@RestControllerAdvice
public class MeteorControllerAdvice {

	/**
	 * CUSTOM_ERROR
	 */
	@ExceptionHandler(MeteorException.class)
	public ResponseEntity<MeteorResponse.MeteorErrorResponse> handleCustomException(MeteorException e) {
		return new ResponseEntity<>(MeteorResponse.error(e.getErrorType()), HttpStatusCode.valueOf(e.getHttpStatus()));
	}
}

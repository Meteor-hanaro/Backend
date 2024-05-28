package com.hana.exception;

import com.hana.response.ErrorType;

import lombok.Getter;

@Getter
public class MeteorException extends RuntimeException {

	private final ErrorType errorType;

	public MeteorException(ErrorType errorType) {
		super(errorType.getMessage());
		this.errorType = errorType;
	}

	public int getHttpStatus() {
		return errorType.getHttpStatusCode();
	}
}

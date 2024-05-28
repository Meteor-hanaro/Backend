package com.hana.exception;

import com.hana.response.ErrorType;

public class BadRequestException extends MeteorException {

	public BadRequestException() {
		super(ErrorType.BAD_REQUEST);
	}

	public BadRequestException(ErrorType errorType) {
		super(errorType);
	}
}

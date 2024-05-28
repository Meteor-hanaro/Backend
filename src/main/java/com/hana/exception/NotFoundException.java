package com.hana.exception;

import com.hana.response.ErrorType;

public class NotFoundException extends MeteorException {

	public NotFoundException() {
		super(ErrorType.BAD_REQUEST);
	}

	public NotFoundException(ErrorType errorType) {
		super(errorType);
	}
}

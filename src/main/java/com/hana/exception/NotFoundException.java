package com.hana.exception;

import com.hana.response.ErrorType;

public class NotFoundException extends MeteorException {

	public NotFoundException() {
		super(ErrorType.NOT_FOUND);
	}

	public NotFoundException(ErrorType errorType) {
		super(errorType);
	}
}

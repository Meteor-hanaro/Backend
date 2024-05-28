package com.hana.exception;

import com.hana.response.ErrorType;

public class InternalServerException extends MeteorException {

	public InternalServerException() {
		super(ErrorType.INTERNAL_SERVER);
	}

	public InternalServerException(ErrorType errorType) {
		super(errorType);
	}
}

package com.hana.exception;

import com.hana.response.ErrorType;

public class UnauthorizedException extends MeteorException {

	public UnauthorizedException() {
		super(ErrorType.UNAUTHORIZED);
	}

	public UnauthorizedException(ErrorType errorType) {
		super(errorType);
	}

}

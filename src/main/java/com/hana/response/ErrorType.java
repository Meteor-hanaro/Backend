package com.hana.response;

import org.springframework.http.HttpStatus;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum ErrorType {

	/**
	 * 400 BAD REQUEST (4000 ~ 4099)
	 */
	BAD_REQUEST(HttpStatus.BAD_REQUEST, 4000, "잘못된 요청입니다."),

	/**
	 * 401 UNAUTHROZIED (4100 ~ 4199)
	 */
	UNAUTHORIZED(HttpStatus.UNAUTHORIZED, 4100, "인증되지 않았습니다."),

	/**
	 * 404 NOT FOUND (4400 ~ 4499)
	 */
	NOT_FOUND(HttpStatus.NOT_FOUND, 4400, "존재하지 않는 리소스입니다."),

	/**
	 * 500 INTERNAL SERVER (5000 ~ 5099)
	 */
	INTERNAL_SERVER(HttpStatus.INTERNAL_SERVER_ERROR, 5000, "서버 에러가 발생했습니다");

	private final HttpStatus httpStatus;
	private final int code;
	private final String message;

	public int getHttpStatusCode() {
		return httpStatus.value();
	}
}

package com.hana.response;

public class MeteorResponse<T> {

	public static <T> MeteorSuccessResponse<T> success(T response) {
		return new MeteorSuccessResponse<>(response);
	}

	public static MeteorErrorResponse error(ErrorType errorType) {
		return new MeteorErrorResponse(errorType.getCode(), errorType.getMessage());
	}

	public static class MeteorSuccessResponse<T> {
		private final T response;

		private MeteorSuccessResponse(T response) {
			this.response = response;
		}

		public T getResponse(){
			return response;
		}
	}

	public static class MeteorErrorResponse {
		private final int code;
		private final String message;

		private MeteorErrorResponse(int code, String message) {
			this.code = code;
			this.message = message;
		}

		public int getCode() {
			return code;
		}

		public String getMessage() {
			return message;
		}
	}
}

package com.practice.dto;

public class ResponseDto {
	private String message;
	private int statusCode;

	public ResponseDto(String message, int code) {
		this.message = message;
		this.statusCode = code;
	}

	public String getMessage() {
		return message;
	}

	public int getStatusCode() {
		return statusCode;
	}

	@Override
	public String toString() {
		return "Response [message=" + message + ", statusCode=" + statusCode + "]";
	}
}

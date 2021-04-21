package com.sapient.rbc.exception;

import lombok.Getter;

@Getter
public class ReviewNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private int code;

	public ReviewNotFoundException() {

	}

	public ReviewNotFoundException(String message) {
		this.message = message;
	}

	public ReviewNotFoundException(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}

}

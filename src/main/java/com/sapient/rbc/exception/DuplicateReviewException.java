package com.sapient.rbc.exception;

import lombok.Getter;

@Getter
public class DuplicateReviewException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private String message;
	private int code;

	public DuplicateReviewException(String message, int code) {
		super();
		this.message = message;
		this.code = code;
	}

}

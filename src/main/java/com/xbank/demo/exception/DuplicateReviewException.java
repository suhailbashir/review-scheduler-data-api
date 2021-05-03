package com.xbank.demo.exception;

import lombok.Getter;

@Getter
public class DuplicateReviewException extends ApiException {

	private static final long serialVersionUID = 1L;

	public DuplicateReviewException(int code, String message) {
		super(code , message);
	}

}

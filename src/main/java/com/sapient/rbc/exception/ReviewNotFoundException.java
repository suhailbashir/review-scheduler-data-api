package com.sapient.rbc.exception;

import lombok.Getter;

@Getter
public class ReviewNotFoundException extends ApiException {

	private static final long serialVersionUID = 1L;

	public ReviewNotFoundException(int code, String message) {
		super(code,message);
	}

}

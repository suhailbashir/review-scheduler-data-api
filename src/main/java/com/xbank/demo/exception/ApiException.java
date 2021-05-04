package com.xbank.demo.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class ApiException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private final int code;
	private final String message;
	
	public ApiException(int code, String message) {
		this.code=code;
		this.message= message;
	}
	
}

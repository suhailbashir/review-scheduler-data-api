package com.sapient.rbc.exception;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
@Setter
public class ApiException extends Exception{

	private static final long serialVersionUID = 1L;
	private  int code;
	private  String message;
	
	public ApiException(int code, String message) {
		this.code=code;
		this.message= message;
	}
	
}

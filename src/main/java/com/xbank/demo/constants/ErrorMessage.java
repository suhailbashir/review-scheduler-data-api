package com.xbank.demo.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

	REVIEW_NOT_FOUND("Review Not Found"),
	DUPLICATE_REVIEW("Duplicate Review"),
	BAD_REQUEST("Bad Request");
	
	String message;
}

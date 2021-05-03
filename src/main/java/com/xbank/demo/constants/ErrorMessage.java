package com.xbank.demo.constants;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {

	REVIEW_NOT_FOUND("Review Not Found"),
	IO_ERROR("IO ERROR"),
	BAD_REQUEST("BAD Request");
	String message;
}

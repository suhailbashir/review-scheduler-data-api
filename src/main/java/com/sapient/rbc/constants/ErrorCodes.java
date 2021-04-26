package com.sapient.rbc.constants;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCodes {

	MANDATORY_PARAM_MISSING("400", "Mandatory Param missing"),
	DATA_NOT_FOUND_ERROR("404", "No Data Found For Request"),
	INTERNAL_ERROR("500", "Internal Error"),
	REQUEST_ERROR("400","Request Error");
	
	String errorCode;
	String errorMessage;
	
}

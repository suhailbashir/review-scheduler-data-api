package com.xbank.demo.pojo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.xbank.demo.constants.AppStatus;
import com.xbank.demo.constants.ErrorCodes;
import com.xbank.demo.constants.ErrorMessage;

public class TestEnums {

	 @Test
	  void Test_AppStatus() {
		
		 assertEquals("SUCCESS", AppStatus.SUCCESS.name());
		 assertEquals("FAILURE", AppStatus.FAILURE.name());
		 
	 }
	
	 
	 @Test
	  void Test_ErrCodes() {
		
		 assertEquals("400", ErrorCodes.MANDATORY_PARAM_MISSING.getErrorCode());
		 assertEquals("Mandatory Param missing", ErrorCodes.MANDATORY_PARAM_MISSING.getErrorMessage());
	 }
	
	 
	 @Test
	  void Test_ErrorMessages() {
		
		 assertEquals("Review Not Found", ErrorMessage.REVIEW_NOT_FOUND.getMessage());
		
		 
	 }
	
	
}

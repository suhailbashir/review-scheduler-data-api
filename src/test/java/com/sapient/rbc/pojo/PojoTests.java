package com.sapient.rbc.pojo;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.jupiter.api.Test;

import com.sapient.rbc.dto.BaseResponse;
import com.sapient.rbc.dto.ReviewDto;
import com.sapient.rbc.dto.Sort;
import com.sapient.rbc.entity.Review;
import com.sapient.rbc.exception.ApiException;
import com.sapient.rbc.exception.DuplicateReviewException;
import com.sapient.rbc.exception.ErrorDetails;
import com.sapient.rbc.exception.ReviewExceptionMessageConstants;
import com.sapient.rbc.exception.ReviewNotFoundException;
 
class PojoTests {

	 
	 @Test
	  void Test_ReviewDto() {
		 final Class<?> classUnderTest = ReviewDto.class;
		 assertPojoMethodsFor(classUnderTest).areWellImplemented();
	 }
	 
	 
	 @Test
	  void Test_ReviewBaseResponse() {
		 final Class<?> classUnderTest = BaseResponse.class;
		 assertPojoMethodsFor(classUnderTest).areWellImplemented();
	 }
	 
	 
	 @Test
	  void Test_Sort() {
		 final Class<?> classUnderTest = Sort.class;
		 assertPojoMethodsFor(classUnderTest).areWellImplemented();
	 }
	 
	 @Test
	  void Test_ApiException() {
		 final Class<?> classUnderTest = ApiException.class;
		 assertPojoMethodsFor(classUnderTest).areWellImplemented();
	 }
	
	 @Test
	  void Test_Review() {
		 final Class<?> classUnderTest = Review.class;
		 assertPojoMethodsFor(classUnderTest).areWellImplemented();
	 }
	
	 @Test
	  void Test_DuplicateReview() {
		 final Class<?> classUnderTest = DuplicateReviewException.class;
		 assertPojoMethodsFor(classUnderTest).areWellImplemented();
	 }
	 
	 @Test
	  void Test_ReviewNotFound() {
		 final Class<?> classUnderTest = ReviewNotFoundException.class;
		 assertPojoMethodsFor(classUnderTest).areWellImplemented();
	 }
	 
	 @Test
	  void Test_ReviewExceptionMessageConstants() {
		 final Class<?> classUnderTest = ReviewExceptionMessageConstants.class;
		 assertPojoMethodsFor(classUnderTest).areWellImplemented();
	 }
	
	 @Test
	  void Test_ErrorDetails() {
		 final Class<?> classUnderTest = ErrorDetails.class;
		 assertPojoMethodsFor(classUnderTest).areWellImplemented();
	 }
	
	 
	
}

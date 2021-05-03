package com.xbank.demo.pojo;

import static pl.pojo.tester.api.assertion.Assertions.assertPojoMethodsFor;

import org.junit.jupiter.api.Test;

import com.xbank.demo.dto.BaseResponse;
import com.xbank.demo.dto.ReviewDto;
import com.xbank.demo.dto.Sort;
import com.xbank.demo.entity.Review;
import com.xbank.demo.exception.ApiException;
import com.xbank.demo.exception.DuplicateReviewException;
import com.xbank.demo.exception.ErrorDetails;
import com.xbank.demo.exception.ReviewExceptionMessageConstants;
import com.xbank.demo.exception.ReviewNotFoundException;
 
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

package com.xbank.demo.exception;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.context.request.WebRequest;

import com.xbank.demo.constants.AppStatus;
import com.xbank.demo.constants.ErrorCodes;
import com.xbank.demo.constants.ErrorMessage;
import com.xbank.demo.dto.BaseResponse;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class ReviewGlobalExceptionHandler{

	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<BaseResponse> handleReviewNotFound(ReviewNotFoundException e, WebRequest request) {
		log.error("ReviewNotFoundException : " , e.getMessage());
		return new ResponseEntity<>(handleError(ErrorCodes.DATA_NOT_FOUND_ERROR.getErrorCode(),ErrorMessage.REVIEW_NOT_FOUND.getMessage()),HttpStatus.NOT_FOUND);
	}
	

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<BaseResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request) {
		log.error("MethodArgumentNotValidException : " , exception.getMessage());
		List<ErrorDetails>errorList=new ArrayList<>();
		exception.getAllErrors().stream()
		.forEach(ex->{
			ErrorDetails errorDetails = ErrorDetails.builder().code(ErrorCodes.REQUEST_ERROR.getErrorCode()).message(ex.getDefaultMessage()).build();
			errorList.add(errorDetails);
		});
		return new ResponseEntity<>(BaseResponse.builder().errorDetails(errorList).status(AppStatus.FAILURE.name()).build(), HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(DuplicateReviewException.class)
	public ResponseEntity<BaseResponse> handleDuplicateReviewException(DuplicateReviewException e, WebRequest request) {
		log.error("DuplicateReviewException : " , e.getMessage());
		return new ResponseEntity<>(handleError(ErrorCodes.REQUEST_ERROR.getErrorCode(),ErrorMessage.BAD_REQUEST.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IOException.class)
	public ResponseEntity<BaseResponse> handleIOException(IOException e, WebRequest request) {
		log.error("IOException : " , e.getMessage());
		return new ResponseEntity<>(handleError(ErrorCodes.REQUEST_ERROR.getErrorCode(),ErrorMessage.IO_ERROR.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(ResourceAccessException.class)
	public ResponseEntity<BaseResponse> handleRAException(ResourceAccessException e, WebRequest request) {
		log.error("ResourceAccessException : " , e.getMessage());
		return new ResponseEntity<>(handleError(ErrorCodes.REQUEST_ERROR.getErrorCode(),ErrorMessage.IO_ERROR.getMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<BaseResponse> globalHttpException(HttpClientErrorException e){
		log.error("HttpClientErrorException : " , e.getMessage());
		return new ResponseEntity<>(handleError(String.valueOf(e.getRawStatusCode()),e.getLocalizedMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<BaseResponse> invalidFormatException(HttpMessageNotReadableException e){
		log.error("HttpMessageNotReadableException : " , e.getMessage());
		return new ResponseEntity<>(handleError(ErrorCodes.REQUEST_ERROR.getErrorCode(),e.getLocalizedMessage()),HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse> globalException(Exception e){
		log.error("Exception : " , e.getMessage());
		return new ResponseEntity<>(handleError(ErrorCodes.INTERNAL_ERROR.getErrorCode(),ErrorCodes.INTERNAL_ERROR.getErrorMessage()),HttpStatus.INTERNAL_SERVER_ERROR);
	}
		

	public BaseResponse handleError(String code, String message) {
	BaseResponse baseResponse=	BaseResponse.builder().build();
		baseResponse.setErrorDetails(Arrays.asList(ErrorDetails.builder().code(code).message(message).build()));
		baseResponse.setStatus("Error");
		return baseResponse;
	}

}

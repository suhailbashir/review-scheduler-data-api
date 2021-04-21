package com.sapient.rbc.exception;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ReviewGlobalExceptionHandler{

	@ExceptionHandler(ReviewNotFoundException.class)
	public ResponseEntity<?> handleReviewNotFound(ReviewNotFoundException exception, WebRequest request) {
		ErrorDetails errorDetails = ErrorDetails.builder().timestamp(LocalDateTime.now()).code(HttpStatus.NOT_FOUND.value()).details(request.getDescription(false)).message(exception.getMessage()).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, WebRequest request) {
		List<ErrorDetails>errorList=new ArrayList<>();
		exception.getAllErrors().stream()
		.forEach(ex->{
			ErrorDetails errorDetails = ErrorDetails.builder().timestamp(LocalDateTime.now()).code(HttpStatus.BAD_REQUEST.value()).details(request.getDescription(false)).message(ex.getDefaultMessage()).build();
			errorList.add(errorDetails);
		});
		return new ResponseEntity<>(errorList, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(DuplicateReviewException.class)
	public ResponseEntity<?> handleDuplicateReviewException(DuplicateReviewException exception, WebRequest request) {
		ErrorDetails errorDetails = ErrorDetails.builder().timestamp(LocalDateTime.now()).code(HttpStatus.FORBIDDEN.value()).details(request.getDescription(false)).message(exception.getMessage()).build();
		return new ResponseEntity<>(errorDetails, HttpStatus.FORBIDDEN);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
				ErrorDetails errorDetails = ErrorDetails.builder().timestamp(LocalDateTime.now()).code(HttpStatus.INTERNAL_SERVER_ERROR.value()).details(request.getDescription(false)).message(exception.getMessage()).build();
				return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

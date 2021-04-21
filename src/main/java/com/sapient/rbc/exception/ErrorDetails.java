package com.sapient.rbc.exception;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorDetails {
	
	private String message;
	private String details;
	private int code;
	private LocalDateTime timestamp;

}

package com.sapient.rbc.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.sapient.rbc.exception.ErrorDetails;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class BaseResponse {
	private static  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-uuuu HH:mm:ss:SSS").withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault());
	private String status;
	private List<ErrorDetails> errorDetails; 
	private  String timestamp = formatter.format(Instant.now());
	private List<ReviewDto> data;
}

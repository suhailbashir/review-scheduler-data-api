package com.xbank.demo.dto;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import com.xbank.demo.exception.ErrorDetails;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseResponse {
	private static  DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-uuuu HH:mm:ss:SSS").withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault());
	private String status;
	private List<ErrorDetails> errorDetails; 
	private final String timestamp = formatter.format(Instant.now());
	
}

package com.xbank.demo.graph.model;

import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ReviewResponseDTO {


	
	private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-uuuu HH:mm:ss:SSS")
				.withLocale(Locale.getDefault()).withZone(ZoneId.systemDefault());
	
	private String status;
	private List<Review> reviews;
	private final String timestamp = formatter.format(Instant.now());
}

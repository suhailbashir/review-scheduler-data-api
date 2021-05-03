package com.xbank.demo.graph.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorDetail {
	
	private String code;
	private String message;

	public String toString() {
		return "{\"code\":"+code.toString()+", \"message\":\""+message+"\"}";
	}
	
}

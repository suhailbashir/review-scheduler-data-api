package com.sapient.rbc.dto;

import java.util.List;

import com.sapient.rbc.exception.ErrorDetails;

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
public class ReviewBaseResponse<T> {
	

	private String status;
	private List<ErrorDetails> errorDetails;

}

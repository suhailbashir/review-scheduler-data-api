package com.xbank.demo.graph.model;

import java.util.List;

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
	private List<ErrorDetail> errorDetails;

}

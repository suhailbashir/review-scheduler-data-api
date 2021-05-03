package com.xbank.demo.graph.model;

import java.io.Serializable;

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
public class Review implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Long reviewId;
	
	private String reviewName;
	
	private String dueForReview;
	
	private String frequency;
	
	private String reviewType;
	
	private String lastUpdated;

}

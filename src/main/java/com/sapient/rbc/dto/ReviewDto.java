package com.sapient.rbc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.sapient.rbc.exception.ReviewExceptionMessageConstants;

import lombok.Data;

@Data
public class ReviewDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Long reviewId;
	
	@NotEmpty(message = "Review Name should not be empty.")
	private String reviewName;
	
	@NotEmpty(message = "Due For Review should not be empty.")
	@Pattern(regexp = ReviewExceptionMessageConstants.DATE_FORMAT_REGEX,message = "Last Updated should be in dd/MM/yyyy format.")
	private String dueForReview;
	
	@NotEmpty(message = "Frequency should not be empty.")
	private String frequency;
	
	@NotEmpty(message = "Review should not be empty.")
	private String reviewType;
	
	@NotEmpty(message = "Last Updated should not be empty.")
	@Pattern(regexp = ReviewExceptionMessageConstants.DATE_FORMAT_REGEX,message = "Last Updated should be in dd/MM/yyyy format.")
	private String lastUpdated;

}

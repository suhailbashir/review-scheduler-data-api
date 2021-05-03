package com.xbank.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.xbank.demo.exception.ReviewExceptionMessageConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long reviewId;
	
	@NotEmpty(message = "Review Name should not be empty.")
	private String reviewName;
	
	@NotEmpty(message = "Due For Review should not be empty.")
	@Pattern(regexp = ReviewExceptionMessageConstants.DATE_FORMAT_REGEX,message = "Due For Review should be in dd/MM/yyyy format.")
	@Schema(description ="dueForReview", type = "date", example = "01/01/2021")
	private String dueForReview;
	
	@NotEmpty(message = "Frequency should not be empty.")
	private String frequency;
	
	@NotEmpty(message = "Review should not be empty.")
	private String reviewType;
	
	@NotEmpty(message = "Last Updated should not be empty.")
	@Pattern(regexp = ReviewExceptionMessageConstants.DATE_FORMAT_REGEX,message = "Last Updated should be in dd/MM/yyyy format.")
	@Schema(description ="lastUpdated", type = "date", example = "01/01/2021")
	private String lastUpdated;

}

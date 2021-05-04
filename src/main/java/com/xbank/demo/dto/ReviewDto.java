package com.xbank.demo.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.xbank.demo.constants.ReviewExceptionMessageConstants;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReviewDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Long reviewId;
	
	@NotEmpty(message ="{reviewname.not.empty.message}")
	private String reviewName;
	
	@NotEmpty(message ="{dueforreview.not.empty.message}")
	@Pattern(regexp = ReviewExceptionMessageConstants.DATE_FORMAT_REGEX,message = "{dueforreview.pattern.message}")
	@Schema(description ="dueForReview", type = "date", example = "01/01/2021")
	private String dueForReview;
	
	@NotEmpty(message = "{frequency.not.empty.message}")
	private String frequency;
	
	@NotEmpty(message = "{reviewtype.not.empty.message}")
	private String reviewType;
	
	@NotEmpty(message = "{lastupdated.not.empty.message}")
	@Pattern(regexp = ReviewExceptionMessageConstants.DATE_FORMAT_REGEX,message = "{lastupdated.pattern.message}")
	@Schema(description ="lastUpdated", type = "date", example = "01/01/2021")
	private String lastUpdated;
}

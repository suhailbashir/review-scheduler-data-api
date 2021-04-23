package com.sapient.rbc.dto;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Min(0)
	@Max(1000)
	@NotNull(message = "PageNumber should not be empty.")
	//@Pattern(regexp = ReviewExceptionMessageConstants.NUMBER_FORMAT_REGEX,message = "PageNumber Should only be a number.")
	private Integer pageNumber;
	
	@Min(0)
	@Max(1000)
	@NotNull(message = "PageSize should not be empty.")
	//@Pattern(regexp = ReviewExceptionMessageConstants.NUMBER_FORMAT_REGEX,message = "PageSize Should only be a number.")
	private Integer pageSize;
    
	
	
	
	private Sort sort;
    
}

package com.sapient.rbc.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
@Schema
@Data
public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Min(0)
	@Max(1000)
	@NotNull(message = "PageNumber should not be empty.")
	@Schema(description ="page number", type = "integer", example = "0")
	private Integer pageNumber=0;
	
	@Min(0)
	@Max(1000)
	@NotNull(message = "PageSize should not be empty.")
	@Schema(description ="page size", type = "integer", example = "10")
	private Integer pageSize=10;
	
	@Valid
	private Sort sort;
    
}

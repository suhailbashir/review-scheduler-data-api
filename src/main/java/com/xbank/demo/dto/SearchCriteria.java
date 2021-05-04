package com.xbank.demo.dto;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;
@Schema
@Data
@Builder
public class SearchCriteria implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Min(0)
	@Max(1000)
	@NotNull(message = "{pageSize.not.empty.message}")
	@Schema(description ="page number", type = "integer", example = "0")
	private Integer pageNumber;
	
	@Min(0)
	@Max(1000)
	@NotNull(message = "{pageSize.not.empty.message}")
	@Schema(description ="page size", type = "integer", example = "10")
	private Integer pageSize;
	
	@Valid
	private Sort sort;
    
}

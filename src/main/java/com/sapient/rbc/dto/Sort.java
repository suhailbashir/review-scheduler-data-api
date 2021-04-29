package com.sapient.rbc.dto;

import java.io.Serializable;

import javax.validation.Valid;

import com.sapient.rbc.validation.DirectionConstraint;
import com.sapient.rbc.validation.SortConstraint;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sort implements Serializable{

	private static final long serialVersionUID = 1L;

	@DirectionConstraint
	@Valid
	@Schema(description ="direction", type = "string", example = "ASC")
	private String direction;
	
	@SortConstraint
	@Valid
	@Schema(description ="sort by", type = "string", example = "reviewName")
	private String sortBy;
	
}

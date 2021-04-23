package com.sapient.rbc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import com.sapient.rbc.validation.SortConstraint;

import lombok.Data;

@Data
public class Sort implements Serializable{

	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "Direction should not be empty.")
	private Direction direction;
	
	@NotEmpty(message = "SortBy should not be empty.")
	@SortConstraint
	private SortBy sortBy;
	
}

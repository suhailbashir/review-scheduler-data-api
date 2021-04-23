package com.sapient.rbc.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.sapient.rbc.dto.SortBy;

public class SortValidator implements ConstraintValidator<SortConstraint, String> {

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		return value != null && value.equals(SortBy.FREQUENCY.toString()) || value.equals(SortBy.REVIEW_NAME.toString());
		
	}

	
}

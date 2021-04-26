package com.sapient.rbc.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DirectionValidator implements ConstraintValidator<DirectionConstraint, String> {
	final private List<String> directionPattern = Arrays.asList("ASC", "DESC");

	@Override
	public void initialize(final DirectionConstraint constraintAnnotation) {
		//for interface
	}

	@Override
	public boolean isValid(final String value, final ConstraintValidatorContext context) {

		return !(value == null || value.isEmpty()) && directionPattern.contains(value);
	}
}

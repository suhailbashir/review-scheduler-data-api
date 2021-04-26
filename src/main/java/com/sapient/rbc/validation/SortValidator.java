package com.sapient.rbc.validation;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class SortValidator implements ConstraintValidator<SortConstraint, String> {

	final private List<String> sortByPattern = Arrays.asList("reviewName","frequency");
    
	@Override
    public void initialize(final SortConstraint constraintAnnotation) {

    }
	
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return !(value == null || value.isEmpty()) && sortByPattern.contains(value);
    }

	
}

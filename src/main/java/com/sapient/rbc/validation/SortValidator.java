package com.sapient.rbc.validation;

import java.util.Arrays;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Value;


public class SortValidator implements ConstraintValidator<SortConstraint, String> {
	
	@Value("${applicableSortByFields}")
	private String applicableSortByFields;

	@Override
    public void initialize(final SortConstraint constraintAnnotation) {
		//comment
    }
	
    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
    	String[] validSearchFields=	applicableSortByFields.split(",");
    	boolean isFieldValid = Arrays.stream(validSearchFields).anyMatch(x -> x.equals(value));
        return !(value == null || value.isEmpty()) && isFieldValid;
    }

	
}

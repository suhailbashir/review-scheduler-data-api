package com.sapient.rbc.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
@Constraint(validatedBy = {DirectionValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(value = RetentionPolicy.RUNTIME)
@Documented
public @interface  DirectionConstraint {
	 String message() default "Invalid Direction.Kindly pass either <ASC> or <DESC>";
	    Class<?>[] groups() default {};
	    Class<? extends Payload>[] payload() default {};
}

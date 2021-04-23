package com.sapient.rbc.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = SortValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface SortConstraint {
    String message() default "Invalid Sort Column.Please pass either <ASCENDING> or <DESCENDING> ";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
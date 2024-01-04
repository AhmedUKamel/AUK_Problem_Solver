package com.ahmedukamel.problemsolver.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MatchPasswordValidator.class)
@Documented
public @interface MatchPassword {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String passwordField();

    String confirmField();
}
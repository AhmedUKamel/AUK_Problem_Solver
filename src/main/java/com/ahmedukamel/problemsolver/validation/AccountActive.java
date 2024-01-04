package com.ahmedukamel.problemsolver.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountActiveValidator.class)
public @interface AccountActive {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}

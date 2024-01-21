package com.ahmedukamel.problemsolver.validation.annotation;

import com.ahmedukamel.problemsolver.validation.validator.AccountLoginValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AccountLoginValidator.class)
@Documented
public @interface AccountLogin {
    String message();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String emailField();

    String passwordField();
}

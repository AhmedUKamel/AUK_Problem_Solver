package com.ahmedukamel.problemsolver.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.BeanWrapperImpl;

public class MatchPasswordValidator implements ConstraintValidator<MatchPassword, Object> {

    private String confirmField;
    private String passwordField;
    private String message;

    @Override
    public void initialize(MatchPassword constraintAnnotation) {
        confirmField = constraintAnnotation.confirmField();
        passwordField = constraintAnnotation.passwordField();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        String password = (String) new BeanWrapperImpl(object).getPropertyValue(passwordField);
        String confirm = (String) new BeanWrapperImpl(object).getPropertyValue(confirmField);
        if (password != null && confirm != null && !password.equals(confirm)) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(confirmField).addConstraintViolation();
            return false;
        }
        return true;
    }
}
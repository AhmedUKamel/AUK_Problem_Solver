package com.ahmedukamel.problemsolver.validation.validator;

import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.repository.UserRepository;
import com.ahmedukamel.problemsolver.validation.annotation.AccountLogin;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

@RequiredArgsConstructor
public class AccountLoginValidator implements ConstraintValidator<AccountLogin, Object> {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private String emailField;
    private String passwordField;
    private String message;

    @Override
    public void initialize(AccountLogin constraintAnnotation) {
        emailField = constraintAnnotation.emailField();
        passwordField = constraintAnnotation.passwordField();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object object, ConstraintValidatorContext context) {
        String email = (String) new BeanWrapperImpl(object).getPropertyValue(emailField);
        String password = (String) new BeanWrapperImpl(object).getPropertyValue(passwordField);
        if (email != null && password != null) {
            Optional<User> optionalUser = repository.findByEmail(email.toLowerCase().strip());
            if (optionalUser.isPresent() && passwordEncoder.matches(password, optionalUser.get().getPassword())) {
                return true;
            }
        }
        context.disableDefaultConstraintViolation();
        context.buildConstraintViolationWithTemplate(message).addPropertyNode(passwordField).addConstraintViolation();
        return false;
    }
}

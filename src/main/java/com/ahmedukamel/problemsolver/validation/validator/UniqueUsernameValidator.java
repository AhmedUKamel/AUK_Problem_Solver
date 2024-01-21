package com.ahmedukamel.problemsolver.validation.validator;

import com.ahmedukamel.problemsolver.repository.UserRepository;
import com.ahmedukamel.problemsolver.validation.annotation.UniqueUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {
    private final UserRepository repository;

    @Override
    public boolean isValid(String username, ConstraintValidatorContext constraintValidatorContext) {
        return username == null || !repository.existsByUsername(username);
    }
}

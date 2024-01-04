package com.ahmedukamel.problemsolver.validation;

import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.repository.UserRepository;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AccountActiveValidator implements ConstraintValidator<AccountActive, String> {
    private final UserRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> optionalUser = repository.findByEmail(email.toLowerCase().strip());
        return optionalUser.isEmpty() || optionalUser.get().isEnabled();
    }
}

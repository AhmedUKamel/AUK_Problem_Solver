package com.ahmedukamel.problemsolver.validation.validator;

import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.repository.UserRepository;
import com.ahmedukamel.problemsolver.validation.annotation.AccountOpen;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@RequiredArgsConstructor
public class AccountOpenValidator implements ConstraintValidator<AccountOpen, String> {
    private final UserRepository repository;

    @Override
    public boolean isValid(String email, ConstraintValidatorContext constraintValidatorContext) {
        Optional<User> optionalUser = repository.findByEmail(email.toLowerCase().strip());
        return optionalUser.isPresent() && optionalUser.get().isAccountNonLocked();
    }
}

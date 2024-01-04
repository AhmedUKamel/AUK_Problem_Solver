package com.ahmedukamel.problemsolver.validation;

import com.ahmedukamel.problemsolver.repository.UserRepository;
import com.ahmedukamel.problemsolver.util.CustomFormatter;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UniquePhoneValidator implements ConstraintValidator<UniquePhone, String> {
    private final UserRepository repository;

    @Override
    public boolean isValid(String phone, ConstraintValidatorContext constraintValidatorContext) {
        return phone == null || !repository.existsByPhone(CustomFormatter.formatPhone(phone));
    }
}

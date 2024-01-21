package com.ahmedukamel.problemsolver.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ahmedukamel.problemsolver.validation.ValidationMessages.INVALID_EMAIL_MESSAGE;
import static com.ahmedukamel.problemsolver.validation.ValidationRegexp.REGEXP_EMAIL;

@Data
@NoArgsConstructor
public class CheckAccountRequest {
    @Pattern(message = INVALID_EMAIL_MESSAGE, regexp = REGEXP_EMAIL)
    private String email;
}

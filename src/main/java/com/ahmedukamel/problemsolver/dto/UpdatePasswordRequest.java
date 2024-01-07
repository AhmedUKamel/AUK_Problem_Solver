package com.ahmedukamel.problemsolver.dto;

import com.ahmedukamel.problemsolver.validation.AccountLogin;
import com.ahmedukamel.problemsolver.validation.MatchPassword;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ahmedukamel.problemsolver.validation.ValidationMessages.*;
import static com.ahmedukamel.problemsolver.validation.ValidationRegexp.REGEXP_PASSWORD;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@AccountLogin(message = WRONG_PASSWORD, emailField = "email", passwordField = "oldPassword")
@MatchPassword(message = MISMATCH_PASSWORD_MESSAGE, passwordField = "newPassword", confirmField = "confirmPassword")
public class UpdatePasswordRequest {
    private String oldPassword;
    @Pattern(message = INVALID_PASSWORD_MESSAGE, regexp = REGEXP_PASSWORD)
    private String newPassword;
    private String confirmPassword;
    private String email;
}

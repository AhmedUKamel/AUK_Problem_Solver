package com.ahmedukamel.problemsolver.dto;

import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.validation.annotation.AccountLogin;
import com.ahmedukamel.problemsolver.validation.annotation.MatchPassword;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.NoArgsConstructor;

import static com.ahmedukamel.problemsolver.validation.ValidationMessages.*;
import static com.ahmedukamel.problemsolver.validation.ValidationRegexp.REGEXP_PASSWORD;

@Data
@NoArgsConstructor
@AccountLogin(message = WRONG_PASSWORD, emailField = "email", passwordField = "oldPassword")
@MatchPassword(message = MISMATCH_PASSWORD_MESSAGE, passwordField = "newPassword", confirmField = "confirmPassword")
public class UpdatePasswordRequest {
    @Pattern(message = INVALID_PASSWORD_MESSAGE, regexp = REGEXP_PASSWORD)
    private String newPassword;
    private String oldPassword;
    private String confirmPassword;
    private String email;

    public UpdatePasswordRequest(User user) {
        this.email = user.getEmail();
    }
}

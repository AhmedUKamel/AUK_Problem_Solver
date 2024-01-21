package com.ahmedukamel.problemsolver.dto;

import com.ahmedukamel.problemsolver.validation.annotation.AccountActive;
import com.ahmedukamel.problemsolver.validation.annotation.AccountLogin;
import com.ahmedukamel.problemsolver.validation.annotation.AccountOpen;
import lombok.Data;

import static com.ahmedukamel.problemsolver.validation.ValidationMessages.*;

@Data
@AccountLogin(message = WRONG_PASSWORD, emailField = "email", passwordField = "password")
public class LoginRequest {
    @AccountActive(message = ACCOUNT_DISABLED)
    @AccountOpen(message = ACCOUNT_LOCKED)
    private String email;
    private String password;

    public LoginRequest(String email) {
        this.email = email;
    }
}

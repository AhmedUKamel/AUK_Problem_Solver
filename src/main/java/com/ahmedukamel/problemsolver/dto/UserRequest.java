package com.ahmedukamel.problemsolver.dto;

import com.ahmedukamel.problemsolver.model.Gender;
import com.ahmedukamel.problemsolver.validation.*;
import jakarta.validation.constraints.*;
import lombok.*;

import static com.ahmedukamel.problemsolver.validation.ValidationMessages.*;
import static com.ahmedukamel.problemsolver.validation.ValidationRegexp.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@MatchPassword(message = MISMATCH_PASSWORD_MESSAGE, passwordField = "password", confirmField = "confirm")
@AccountLogin(message = WRONG_PASSWORD, emailField = "email", passwordField = "password")
public class UserRequest {
    @Pattern(message = INVALID_EMAIL_MESSAGE, regexp = REGEXP_EMAIL)
    @AccountActive(message = ACCOUNT_DISABLED)
    @AccountOpen(message = ACCOUNT_LOCKED)
    private String email;
    @Pattern(message = INVALID_PASSWORD_MESSAGE, regexp = REGEXP_PASSWORD)
    private String password;
    @Pattern(message = INVALID_NAME_MESSAGE, regexp = REGEXP_NAME)
    private String name;
    @Pattern(message = INVALID_TITLE_MESSAGE, regexp = REGEXP_TITLE)
    private String title;
    @Pattern(message = INVALID_PHONE_MESSAGE, regexp = REGEXP_PHONE)
    @UniquePhone(message = EXIST_PHONE_MESSAGE)
    private String phone;
    @Size(message = INVALID_BIO_MESSAGE, min = 10)
    private String bio;
    @UniqueUsername(message = EXIST_USERNAME_MESSAGE)
    private String username;
    private String confirm;
    private String picture;
    private Gender gender;
}
package com.ahmedukamel.problemsolver.validation;

public interface ValidationMessages {
    String EXIST_USERNAME_MESSAGE = "Username is already exists";
    String EXIST_PHONE_MESSAGE = "Phone number is already exists";
    String INVALID_EMAIL_MESSAGE = "Invalid Gmail address, must match example@gmail.com";
    String INVALID_PHONE_MESSAGE = "Invalid phone number, must match 01XXXXXXXXX";
    String INVALID_NAME_MESSAGE = "Invalid name, must match from 1 to 4 only characters names";
    String INVALID_BIO_MESSAGE = "Invalid bio, minimum length is 10 characters";
    String INVALID_PASSWORD_MESSAGE = "Invalid password, must match characters, at least one uppercase letter, one lowercase letter and one number";
    String INVALID_TITLE_MESSAGE = "Invalid title, must match from 1 to 4 characters and @-_()";
    String MISMATCH_PASSWORD_MESSAGE = "Invalid confirm password, must match password";
    String ACCOUNT_DISABLED = "Account is not activated yet";
    String ACCOUNT_LOCKED = "Account is locked by moderator";
    String WRONG_PASSWORD = "Wrong password for this account";
}

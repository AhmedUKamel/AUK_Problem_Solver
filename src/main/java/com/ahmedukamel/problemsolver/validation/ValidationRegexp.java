package com.ahmedukamel.problemsolver.validation;

public interface ValidationRegexp {
    String REGEXP_EMAIL = "^[a-zA-Z0-9](.?[a-zA-Z0-9]){5,}@gmail.com$";
    String REGEXP_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d ]{8,}$";
    String REGEXP_NAME = "^[a-zA-Z]{3,}(?: [a-zA-Z]+){0,3}$";
    String REGEXP_PHONE = "^01\\d{9}$";
    String REGEXP_TITLE = "^[a-zA-Z@-_()]{3,}(?: [a-zA-Z@-_()]+){0,3}$";
}

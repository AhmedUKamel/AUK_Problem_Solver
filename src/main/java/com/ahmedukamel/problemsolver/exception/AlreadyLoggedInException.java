package com.ahmedukamel.problemsolver.exception;

public class AlreadyLoggedInException extends RuntimeException {
    public AlreadyLoggedInException() {
        super("You are already logged in");
    }
}

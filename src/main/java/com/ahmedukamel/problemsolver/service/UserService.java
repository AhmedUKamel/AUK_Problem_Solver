package com.ahmedukamel.problemsolver.service;

import com.ahmedukamel.problemsolver.model.User;

import java.util.Optional;

public interface UserService {
    boolean isEmailExists(String email);
}

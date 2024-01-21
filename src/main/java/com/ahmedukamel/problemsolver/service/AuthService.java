package com.ahmedukamel.problemsolver.service;

import com.ahmedukamel.problemsolver.dto.LoginRequest;
import com.ahmedukamel.problemsolver.dto.RegisterRequest;

public interface AuthService {
    void login(LoginRequest user);

    void register(RegisterRequest user);
}

package com.ahmedukamel.problemsolver.service;

import com.ahmedukamel.problemsolver.dto.UserRequest;
import com.ahmedukamel.problemsolver.model.User;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    boolean login(HttpServletRequest request, UserRequest user);

    boolean register(UserRequest user);

    void authenticateSession(HttpServletRequest request, User user, String password);
}

package com.ahmedukamel.problemsolver.service;

import com.ahmedukamel.problemsolver.dto.UserRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface AuthService {
    boolean login(HttpServletRequest request, UserRequest user);
    boolean register(UserRequest user);
}

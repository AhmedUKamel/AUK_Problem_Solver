package com.ahmedukamel.problemsolver.impl;

import com.ahmedukamel.problemsolver.dto.UpdateUserRequest;
import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.repository.UserRepository;
import com.ahmedukamel.problemsolver.service.AuthService;
import com.ahmedukamel.problemsolver.service.ProfileService;
import com.ahmedukamel.problemsolver.util.AuthenticatedUser;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;


    @Override
    public void updatePassword(HttpServletRequest request, String newPassword) {
        User user = AuthenticatedUser.getAuthenticatedUser();
        if (user != null) {
            user.setPassword(passwordEncoder.encode(newPassword));
            repository.save(user);
            authService.authenticateSession(request, user, newPassword);
        }
    }

    @Override
    public void updateProfile(UpdateUserRequest request) {
        User user = AuthenticatedUser.getAuthenticatedUser();
        if (user != null) {
            user.setName(request.getName());
            user.setTitle(request.getTitle());
            user.setPicture(request.getPicture());
            repository.save(user);
        }
    }
}
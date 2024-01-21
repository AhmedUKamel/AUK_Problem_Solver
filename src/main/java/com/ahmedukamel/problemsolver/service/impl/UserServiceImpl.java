package com.ahmedukamel.problemsolver.service.impl;

import com.ahmedukamel.problemsolver.dto.UpdateUserRequest;
import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.repository.UserRepository;
import com.ahmedukamel.problemsolver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    @Override
    public boolean isEmailExists(String email) {
        return repository.existsByEmail(email.toLowerCase().strip());
    }

    @Override
    public void updatePassword(User user, String newPassword) {
        user.setPassword(passwordEncoder.encode(newPassword));
        repository.save(user);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), newPassword));
    }

    @Override
    public void updateProfile(User user, UpdateUserRequest request) {
        user.setName(request.getName());
        user.setTitle(request.getTitle());
        user.setPicture(request.getPicture());
        repository.save(user);
    }
}

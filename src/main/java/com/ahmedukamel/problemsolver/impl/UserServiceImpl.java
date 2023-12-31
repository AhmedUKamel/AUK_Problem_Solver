package com.ahmedukamel.problemsolver.impl;

import com.ahmedukamel.problemsolver.repository.UserRepository;
import com.ahmedukamel.problemsolver.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Override
    public boolean isEmailExists(String email) {
        return repository.existsByEmail(email.toLowerCase().strip());
    }
}

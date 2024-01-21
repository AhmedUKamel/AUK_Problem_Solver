package com.ahmedukamel.problemsolver.service;

import com.ahmedukamel.problemsolver.dto.UpdateUserRequest;
import com.ahmedukamel.problemsolver.model.User;

public interface UserService {
    boolean isEmailExists(String email);

    void updatePassword(User user, String newPassword);

    void updateProfile(User user, UpdateUserRequest request);
}

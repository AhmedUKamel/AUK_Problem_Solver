package com.ahmedukamel.problemsolver.service;


import com.ahmedukamel.problemsolver.dto.UpdateUserRequest;
import jakarta.servlet.http.HttpServletRequest;

public interface ProfileService {
    void updatePassword(HttpServletRequest request, String newPassword);

    void updateProfile(UpdateUserRequest request);
}

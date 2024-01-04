package com.ahmedukamel.problemsolver.mapper;

import com.ahmedukamel.problemsolver.dto.UserRequest;
import com.ahmedukamel.problemsolver.model.Role;
import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.util.CustomFormatter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;

@Configuration
@RequiredArgsConstructor
public class UserMapper {
    private final PasswordEncoder passwordEncoder;

    public User fromRequest(UserRequest user) {
        return User
                .builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail().toLowerCase().strip())
                .name(user.getName().strip())
                .phone(CustomFormatter.formatPhone(user.getPhone().strip()))
                .picture(user.getPicture().strip())
                .title(user.getTitle().strip())
                .bio(user.getBio().strip())
                .gender(user.getGender())
                .rate(0)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .enabled(false)
                .roles(Set.of(Role.STUDENT))
                .build();
    }
}
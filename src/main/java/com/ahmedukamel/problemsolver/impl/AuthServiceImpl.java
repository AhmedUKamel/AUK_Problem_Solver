package com.ahmedukamel.problemsolver.impl;

import com.ahmedukamel.problemsolver.dto.UserRequest;
import com.ahmedukamel.problemsolver.mapper.UserMapper;
import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.repository.UserRepository;
import com.ahmedukamel.problemsolver.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserMapper userMapper;

    @Override
    public boolean login(HttpServletRequest request, UserRequest user) {
        Optional<User> optionalUser = repository.findByEmail(user.getEmail().toLowerCase().strip());
        if (optionalUser.isPresent() && passwordEncoder.matches(user.getPassword(), optionalUser.get().getPassword())) {
            authenticateSession(request, optionalUser.get(), user.getPassword());
            return true;
        }
        return false;
    }

    @Override
    public boolean register(UserRequest user) {
        try {
            repository.save(userMapper.fromRequest(user));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void authenticateSession(HttpServletRequest request, User user, String password) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), password, user.getAuthorities());
        Authentication authenticated = authenticationManager.authenticate(authentication);
        SecurityContext context = SecurityContextHolder.getContext();
        context.setAuthentication(authenticated);
        request.getSession(true).setAttribute("SPRING_SECURITY_CONTEXT", context);
    }
}

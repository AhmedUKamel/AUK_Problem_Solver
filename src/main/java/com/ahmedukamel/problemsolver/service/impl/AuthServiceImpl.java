package com.ahmedukamel.problemsolver.service.impl;

import com.ahmedukamel.problemsolver.dto.LoginRequest;
import com.ahmedukamel.problemsolver.dto.RegisterRequest;
import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.repository.UserRepository;
import com.ahmedukamel.problemsolver.service.AccountVerificationService;
import com.ahmedukamel.problemsolver.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final AccountVerificationService accountVerificationService;

    @Override
    public void login(LoginRequest user) {
        String email = user.getEmail().toLowerCase().strip();
        Optional<User> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isPresent() && passwordEncoder.matches(user.getPassword(), optionalUser.get().getPassword())) {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, user.getPassword()));
        }
    }

    @Override
    public void register(RegisterRequest request) {
        String password = request.getPassword();
        request.setPassword(passwordEncoder.encode(password));
        User user = userRepository.save(new User(request));
        accountVerificationService.sendVerificationEmail(user);
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), password));
    }
}

package com.ahmedukamel.problemsolver.service;

import com.ahmedukamel.problemsolver.model.User;
import com.ahmedukamel.problemsolver.model.AccountVerificationToken;
import com.ahmedukamel.problemsolver.repository.UserRepository;
import com.ahmedukamel.problemsolver.repository.VerificationEmailRepository;
import com.ahmedukamel.problemsolver.util.EmailTemplateUtils;
import com.ahmedukamel.problemsolver.util.TokenStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class AccountVerificationService {
    private final UserRepository userRepository;
    private final VerificationEmailRepository emailRepository;
    private final EmailSenderService emailSenderService;

    public void sendVerificationEmail(User user) {
        AccountVerificationToken token = emailRepository.save(new AccountVerificationToken(user));
        String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/accounts/verify/" + token.getId()).toUriString();
        String text = EmailTemplateUtils.getVerificationEmail(user.getName(), url);
        emailSenderService.sendEmail(user.getEmail(), "Email Verification", text);
    }

    public TokenStatus verifyToken(String token) {
        Optional<AccountVerificationToken> tokenOptional = emailRepository.findById(token);
        if (tokenOptional.isEmpty()) {
            return TokenStatus.INVALID;
        }
        User user = tokenOptional.get().getUser();
        if (user.isEnabled()) {
            return TokenStatus.USED;
        }
        user.setEnabled(true);
        userRepository.save(user);
        return TokenStatus.ACCEPTED;
    }
}

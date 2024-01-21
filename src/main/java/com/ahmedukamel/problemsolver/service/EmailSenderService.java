package com.ahmedukamel.problemsolver.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSenderService {
    @Value("${app.email}")
    private String sender;
    private final JavaMailSender mailSender;

    public void sendEmail(String receiver, String subject, String text) {
        SimpleMailMessage email = new SimpleMailMessage();
        email.setFrom(sender);
        email.setTo(receiver);
        email.setSubject(subject);
        email.setText(text);
        mailSender.send(email);
    }
}

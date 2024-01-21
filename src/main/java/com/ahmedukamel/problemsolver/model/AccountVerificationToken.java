package com.ahmedukamel.problemsolver.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "account_verification_token")
public class AccountVerificationToken {
    @Id
    private String id;
    @OneToOne
    @JoinColumn(nullable = false)
    private User user;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime creation;

    public AccountVerificationToken(User user) {
        this.id = UUID.randomUUID().toString();
        this.user = user;
    }
}
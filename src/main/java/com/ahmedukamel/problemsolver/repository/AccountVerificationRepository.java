package com.ahmedukamel.problemsolver.repository;

import com.ahmedukamel.problemsolver.model.AccountVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountVerificationRepository extends JpaRepository<AccountVerificationToken, String> {
}

package com.satyam.hrportal.repository;

import com.satyam.hrportal.model.AuthToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface AuthTokenRepository extends JpaRepository<AuthToken, String> {
    AuthToken findByAuthToken(String authToken);

    @Modifying
    @Query("DELETE FROM AuthToken a WHERE a.expiryDateTime < :now")
    void deleteAllExpiredToken(LocalDateTime now);
}

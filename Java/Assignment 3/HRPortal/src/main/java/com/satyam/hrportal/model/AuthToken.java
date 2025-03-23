package com.satyam.hrportal.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Setter
@Getter
@Entity
public class AuthToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Authid;

    @Column(name = "auth_token", unique = true, nullable = false)
    private String authToken;
    @Column(name = "\"user\"", nullable = false)
    private String user;
    @Column(name = "expiry_date_time", nullable = false)
    private LocalDateTime expiryDateTime;

    public AuthToken() {}

}

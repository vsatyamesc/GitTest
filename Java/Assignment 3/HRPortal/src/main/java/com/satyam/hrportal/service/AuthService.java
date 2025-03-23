package com.satyam.hrportal.service;

import com.satyam.hrportal.model.AuthToken;
import com.satyam.hrportal.repository.AuthTokenRepository;
import com.satyam.hrportal.repository.HRRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AuthService {
    @Autowired
    private AuthTokenRepository authTokenRepository;
    @Autowired
    private HRService hrService;
    public String authenticate(String username, String password){
        if (isValidUser(username, password)){
            String token = UUID.randomUUID().toString();
            AuthToken authToken = new AuthToken();
            authToken.setAuthToken(token);
            authToken.setUser(username);
            authToken.setExpiryDateTime(LocalDateTime.now().plusHours(1));
            authTokenRepository.save(authToken);
            return token;
        }
        return null;
    }

    private boolean isValidUser(String username, String password){
        // is Valid HR
        return hrService.hrAuth(username, password);
    }

    public void invalidateToken(){
        authTokenRepository.deleteAllExpiredToken(LocalDateTime.now());
    }
}

package com.satyam.hrportal.controller;
import com.satyam.hrportal.model.AuthToken;
import com.satyam.hrportal.repository.AuthTokenRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.ServletException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

@Component
public class FilterController extends OncePerRequestFilter {
    @Autowired
    private AuthTokenRepository authTokenRepository;
    private static List<String> PROTECTED_URLS = List.of(
            "/employees",
            "/employees/add",
            "/employees/edit",
            "/employees/delete"
            );

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String requestUrl = request.getRequestURI();
        boolean isProtected = PROTECTED_URLS.stream().anyMatch(requestUrl::startsWith);
        if (isProtected) {
            boolean authenticated = false;
            Cookie[] cookies = request.getCookies();
            if (cookies != null) {
                authenticated = Arrays.stream(cookies)
                        .filter(cookie -> "authToken".equals(cookie.getName()))
                        .anyMatch(cookie -> validateAuthToken(cookie.getValue()));

            }

            if(!authenticated){
                response.sendRedirect("/login");
                return;
            }
        }
        chain.doFilter(request, response);
    };

    private boolean validateAuthToken(String authToken){
        if (authToken == null) {
            return false;
        }
        AuthToken token = authTokenRepository.findByAuthToken(authToken);
        return token != null && token.getExpiryDateTime().isAfter(LocalDateTime.now());
    }
}

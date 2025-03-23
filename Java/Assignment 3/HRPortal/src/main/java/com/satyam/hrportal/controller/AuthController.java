package com.satyam.hrportal.controller;

import com.satyam.hrportal.repository.LoginRequest;
import com.satyam.hrportal.service.AuthService;
import com.satyam.hrportal.service.HRService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Controller
public class AuthController {
    @Autowired
    private HRService hrService;

    @Autowired
    private AuthService authService;

    @GetMapping("/")
    public String loginPage(){
        return "forward:/login.html";
    }
    @GetMapping("/login")
    public String Relogin(){
        return "forward:/login.html";
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest,
                        HttpServletResponse response, Model model) {
        String token = authService.authenticate(loginRequest.getEmail(), loginRequest.getPassword());
        if (token != null) {
            Cookie cookie = new Cookie("authToken", token);
            cookie.setPath("/");
            cookie.setHttpOnly(true);
            cookie.setMaxAge(3600);
            response.addCookie(cookie);

            Map<String, String> responseMap = new HashMap<>();
            responseMap.put("redirect", "/dashboard.html");
            return ResponseEntity.ok(responseMap);
        }
        model.addAttribute("error", "Invalid credentials");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");

    }
    @GetMapping("/logout")
    public String logout(HttpServletResponse response){
        Cookie cookie = new Cookie("authToken", null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
        return "redirect:/login";
    }
}

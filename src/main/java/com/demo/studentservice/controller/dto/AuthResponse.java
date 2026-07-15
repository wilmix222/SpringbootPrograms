package com.demo.studentservice.controller.dto;

import java.util.List;

public class AuthResponse {
    private String token;
    private String tokenType = "Bearer";
    private String username;
    private List<String> roles;

    public AuthResponse(String token, String username, List<String> roles) {
        this.token = token;
        this.username = username;
        this.roles = roles;
    }

    public String getToken() { return token; }
    public String getTokenType() { return tokenType; }
    public String getUsername() { return username; }
    public List<String> getRoles() { return roles; }
}
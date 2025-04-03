package com.example.carmonasportoutlet.security;


public class VerificationRequest {
    private String email;
    private String username;
    private String verificationToken;

    // Getters y Setters
    public String getEmail() {
        return email;
    }
    public String getUsername() {
        return username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getVerificationToken() {
        return verificationToken;
    }

    public void setVerificationToken(String verificationToken) {
        this.verificationToken = verificationToken;
    }
}

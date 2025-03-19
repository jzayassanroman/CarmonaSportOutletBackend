package com.example.carmonasportoutlet.security;
import lombok.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRequest {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

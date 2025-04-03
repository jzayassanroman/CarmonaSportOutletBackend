package com.example.carmonasportoutlet.security;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroRequest {
    private String username;
    private String password;
    private String nombre;
    private String apellido;
    private Integer telefono;
    private String direccion;
    private String email;
}

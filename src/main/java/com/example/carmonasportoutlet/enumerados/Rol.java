package com.example.carmonasportoutlet.enumerados;

public enum Rol {
    ADMINISTRADOR,
    USUARIO;
    public String getNombre() {
        return name(); // Devuelve el nombre del enumerado como String
    }
}

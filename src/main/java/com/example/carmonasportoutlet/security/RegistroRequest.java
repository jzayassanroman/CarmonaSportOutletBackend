package com.example.carmonasportoutlet.security;
import com.example.carmonasportoutlet.entity.User;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.Objects;

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

    @OneToOne
    @JoinColumn(name = "id_usuario", referencedColumnName = "id")
    private User user;

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEmail() {
        return email;
    }

    public User getUser() {
        return user;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RegistroRequest that = (RegistroRequest) o;
        return Objects.equals(username, that.username) && Objects.equals(password, that.password) && Objects.equals(nombre, that.nombre) && Objects.equals(apellido, that.apellido) && Objects.equals(telefono, that.telefono) && Objects.equals(direccion, that.direccion) && Objects.equals(email, that.email) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, password, nombre, apellido, telefono, direccion, email, user);
    }

    @Override
    public String toString() {
        return "RegistroRequest{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", telefono=" + telefono +
                ", direccion='" + direccion + '\'' +
                ", email='" + email + '\'' +
                ", user=" + user +
                '}';
    }

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

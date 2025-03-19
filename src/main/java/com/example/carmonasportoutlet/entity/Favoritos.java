package com.example.carmonasportoutlet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "favoritos", schema = "carmonasportoutlet", catalog = "postgres")
public class Favoritos {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favoritos favoritos = (Favoritos) o;
        return Objects.equals(id, favoritos.id) && Objects.equals(usuario, favoritos.usuario) && Objects.equals(producto, favoritos.producto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usuario, producto);
    }

    @Override
    public String toString() {
        return "Favoritos{" +
                "id=" + id +
                ", usuario=" + usuario +
                ", producto=" + producto +
                '}';
    }
}

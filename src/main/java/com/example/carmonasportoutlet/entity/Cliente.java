
package com.example.carmonasportoutlet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "cliente", schema = "carmonasportoutlet", catalog = "postgres")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private Integer telefono;

    @Column(nullable = false, length = 100)
    private String direccion;

    @OneToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Producto> productos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Valoraciones> valoraciones;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void setValoraciones(List<Valoraciones> valoraciones) {
        this.valoraciones = valoraciones;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return Objects.equals(id, cliente.id) && Objects.equals(nombre, cliente.nombre) && Objects.equals(apellido, cliente.apellido) && Objects.equals(email, cliente.email) && Objects.equals(telefono, cliente.telefono) && Objects.equals(direccion, cliente.direccion) && Objects.equals(usuario, cliente.usuario) && Objects.equals(pedidos, cliente.pedidos) && Objects.equals(productos, cliente.productos) && Objects.equals(valoraciones, cliente.valoraciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nombre, apellido, email, telefono, direccion, usuario, pedidos, productos, valoraciones);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", email='" + email + '\'' +
                ", telefono=" + telefono +
                ", direccion='" + direccion + '\'' +
                ", usuario=" + usuario +
                ", pedidos=" + pedidos +
                ", productos=" + productos +
                ", valoraciones=" + valoraciones +
                '}';
    }
}

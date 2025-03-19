package com.example.carmonasportoutlet.entity;

import com.example.carmonasportoutlet.enumerados.Entrega;
import com.example.carmonasportoutlet.enumerados.Estado;
import com.example.carmonasportoutlet.enumerados.EstadoProducto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "producto", schema = "carmonasportoutlet")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "nombre" ,nullable = false, length = 100)
    private String nombre;

    @Column(name = "tipo" ,nullable = false, length = 100)
    private String tipo;

    @Column(name = "descripcion" ,nullable = false, length = 150)
    private String descripcion;

    @Column(name = "precio" ,nullable = false)
    private Float precio;

    @Column(name = "imagen1" ,nullable = false, length = 300)
    private String imagen1;

    @Column(name = "imagen2" ,length = 300)
    private String imagen2;

    @Column(name = "imagen3" ,length = 300)
    private String imagen3;

    @Column(name = "imagen4" ,length = 300)
    private String imagen4;

    @Column(name = "entrega",nullable = false, length = 100)
    @Enumerated(EnumType.ORDINAL)
    private Entrega entrega;

    @Column(name = "estado",nullable = false, length = 50)
    @Enumerated(EnumType.ORDINAL)
    private EstadoProducto estado;

    @Column(name = "disponible" ,nullable = false, length = 50)
    private boolean disponible;



    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Valoraciones> valoraciones;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<PedidoProducto> pedidoProductos;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<DetalleVenta> detalleVentas;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Favoritos> favoritos;

    // Getter for id
    public Integer getId() {
        return id;
    }

    // Getter for nombre
    public String getNombre() {
        return nombre;
    }

    // Getter for tipo
    public String getTipo() {
        return tipo;
    }

    // Getter for descripcion
    public String getDescripcion() {
        return descripcion;
    }

    // Getter for precio
    public Float getPrecio() {
        return precio;
    }

    // Getter for imagen1
    public String getImagen1() {
        return imagen1;
    }

    // Getter for imagen2
    public String getImagen2() {
        return imagen2;
    }

    // Getter for imagen3
    public String getImagen3() {
        return imagen3;
    }

    // Getter for imagen4
    public String getImagen4() {
        return imagen4;
    }

    // Getter for entrega
    public Entrega getEntrega() {
        return entrega;
    }

    // Getter for estado
    public EstadoProducto getEstado() {
        return estado;
    }

    // Getter for disponible
    public boolean isDisponible() {
        return disponible;
    }

    // Getter for cliente
    public Cliente getCliente() {
        return cliente;
    }

    // Setter for nombre
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Setter for tipo
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    // Setter for descripcion
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    // Setter for precio
    public void setPrecio(Float precio) {
        this.precio = precio;
    }

    // Setter for imagen1
    public void setImagen1(String imagen1) {
        this.imagen1 = imagen1;
    }

    // Setter for imagen2
    public void setImagen2(String imagen2) {
        this.imagen2 = imagen2;
    }

    // Setter for imagen3
    public void setImagen3(String imagen3) {
        this.imagen3 = imagen3;
    }

    // Setter for imagen4
    public void setImagen4(String imagen4) {
        this.imagen4 = imagen4;
    }

    // Setter for entrega
    public void setEntrega(Entrega entrega) {
        this.entrega = entrega;
    }

    // Setter for estado
    public void setEstado(EstadoProducto estado) {
        this.estado = estado;
    }

    // Setter for disponible
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    // Setter for cliente
    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }
}

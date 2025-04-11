package com.example.carmonasportoutlet.DTO;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoClienteDTO {
    private Integer id;
    private String nombre;
    private String tipo;
    private String descripcion;
    private Float precio;
    private String imagen1;
    private String imagen2;
    private String imagen3;
    private String imagen4;
    private String entrega;
    private String estado;
    private boolean disponible;

    public ProductoClienteDTO(Integer id, String nombre, String tipo, String descripcion, Float precio,
                              String imagen1, String imagen2, String imagen3, String imagen4,
                              String entrega, String estado, boolean disponible) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.precio = precio;
        this.imagen1 = imagen1;
        this.imagen2 = imagen2;
        this.imagen3 = imagen3;
        this.imagen4 = imagen4;
        this.entrega = entrega;
        this.estado = estado;
        this.disponible = disponible;
    }
}

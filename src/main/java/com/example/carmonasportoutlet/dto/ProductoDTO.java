package com.example.carmonasportoutlet.dto;


import com.example.carmonasportoutlet.enumerados.Entrega;
import com.example.carmonasportoutlet.enumerados.EstadoProducto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoDTO {
    private String nombre;
    private String tipo;
    private String descripcion;
    private Float precio;
    private String imagen1;
    private String imagen2;
    private String imagen3;
    private String imagen4;
    private Entrega entrega;
    private EstadoProducto estado;
    private boolean disponible;
    private Integer idCliente;
}

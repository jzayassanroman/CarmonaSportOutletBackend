package com.example.carmonasportoutlet.dto;

import com.example.carmonasportoutlet.entity.Producto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponseDTO {
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
    private Boolean disponible;
    private String clientName;  // Nombre del cliente
    private Integer userId;     // ID del usuario dueño del producto

    // Constructor que convierte Producto a ProductoResponseDTO
    public ProductoResponseDTO(Producto producto) {
        this.id = producto.getId();
        this.nombre = producto.getNombre();
        this.tipo = producto.getTipo();
        this.descripcion = producto.getDescripcion();
        this.precio = producto.getPrecio();
        this.imagen1 = producto.getImagen1();
        this.imagen2 = producto.getImagen2();
        this.imagen3 = producto.getImagen3();
        this.imagen4 = producto.getImagen4();
        this.entrega = producto.getEntrega().name();
        this.estado = producto.getEstado().name();

        if (producto.getCliente() != null) {
            this.clientName = producto.getCliente().getNombre();

            // Añadimos la ID del usuario asociado al cliente
            if (producto.getCliente().getUsuario() != null) {
                this.userId = producto.getCliente().getUsuario().getId();
            }
        }
    }
}

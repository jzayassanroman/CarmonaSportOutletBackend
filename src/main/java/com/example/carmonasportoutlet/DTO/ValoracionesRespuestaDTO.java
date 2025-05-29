package com.example.carmonasportoutlet.dto;

import com.example.carmonasportoutlet.entity.Valoraciones;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ValoracionesRespuestaDTO {
    private Integer id;
    private String valoracion;
    private Integer estrellas;
    private LocalDateTime fecha;
    private String nombreCliente;

    // Constructor
    public ValoracionesRespuestaDTO(Valoraciones valoracion) {
        this.id = valoracion.getId();
        this.valoracion = valoracion.getValoracion();
        this.estrellas = valoracion.getEstrellas();
        this.fecha = valoracion.getFecha();
        this.nombreCliente = valoracion.getCliente().getNombre(); // o como se llame
    }

    // Getters y setters
}


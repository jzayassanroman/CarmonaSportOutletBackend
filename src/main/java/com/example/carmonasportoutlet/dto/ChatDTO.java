package com.example.carmonasportoutlet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatDTO {
    private Integer id;
    private Integer remitenteId;
    private Integer destinatarioId;
    private Integer productoId;
    private String nombreProducto; // Nuevo campo
    private Date fechacreacion;

    // Constructor sin nombreProducto (para compatibilidad con el código existente)
    public ChatDTO(Integer id, Integer remitenteId, Integer destinatarioId, Integer productoId, Date fechacreacion) {
        this.id = id;
        this.remitenteId = remitenteId;
        this.destinatarioId = destinatarioId;
        this.productoId = productoId;
        this.fechacreacion = fechacreacion;
    }
}
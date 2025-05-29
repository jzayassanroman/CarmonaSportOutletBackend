package com.example.carmonasportoutlet.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionesDTO {
    private String valoracion;
    private Integer estrellas;
    private Integer idCliente;
    private Integer idClienteValorado;
    private Integer idProducto;

    public ValoracionesDTO(Integer id, String valoracion, LocalDateTime fecha, Integer estrellas, Integer id1, Integer id2) {
    }
}

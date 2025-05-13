package com.example.carmonasportoutlet.dto;

import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistorialPedidoDTO {
    private Integer idPedido;
    private Float total;
    private String estado;
    private Date fecha;
    private String nombreProducto;
}

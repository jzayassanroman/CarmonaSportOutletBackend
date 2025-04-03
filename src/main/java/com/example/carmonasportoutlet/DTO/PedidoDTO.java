package com.example.carmonasportoutlet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class PedidoDTO {
    private Integer id;
    private Float total;
    private String estado;
    private Date fecha;
    private Integer idCliente;
    private Integer idProducto;
}

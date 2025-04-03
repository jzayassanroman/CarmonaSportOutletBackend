package com.example.carmonasportoutlet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ValoracionesDTO {
    private Integer id;
    private String valoracion;
    private Date fecha;
    private Integer estrellas;
    private Integer idCliente;
    private Integer idClienteValorado;
}

package com.example.carmonasportoutlet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FavoritosDTO {
    private Integer id;
    private Integer idUsuario;
    private Integer idProducto;
}

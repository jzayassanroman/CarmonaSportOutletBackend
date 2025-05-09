package com.example.carmonasportoutlet.dto;

import com.example.carmonasportoutlet.enumerados.Provincia;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientePerfilDTO {
    private Integer id;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;
    private String direccion;
    private Provincia provincia;
}

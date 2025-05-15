package com.example.carmonasportoutlet.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MensajeDTO {
    private Integer id;
    private Integer chatId;
    private Integer emisorId;
    private String contenido;
    private Date fechaenvio;
}

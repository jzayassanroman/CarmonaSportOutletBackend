package com.example.carmonasportoutlet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "mensaje", schema = "carmonasportoutlet")
public class Mensaje {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Chat al que pertenece el mensaje
    @ManyToOne
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    // Usuario que lo envía
    @ManyToOne
    @JoinColumn(name = "emisor_id", nullable = false)
    private User emisor;

    private String contenido;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechaenvio")
    private Date fechaenvio;  // O usa LocalDateTime si lo prefieres

}

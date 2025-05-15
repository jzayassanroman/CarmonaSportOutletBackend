package com.example.carmonasportoutlet.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "chat", schema = "carmonasportoutlet")
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // Usuario que inicia la conversación
    @ManyToOne
    @JoinColumn(name = "remitente_id", nullable = false)
    private User remitente;

    // Usuario que recibe (vendedor)
    @ManyToOne
    @JoinColumn(name = "destinatario_id", nullable = false)
    private User destinatario;

    // Producto sobre el cual se habla
    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    private Producto producto;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL)
    private List<Mensaje> mensajes;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fechacreacion")
    private Date fechacreacion;

}

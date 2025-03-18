package com.example.carmonasportoutlet.entity;

import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.entity.Producto;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "valoraciones", schema = "carmonasportoutlet")
public class Valoraciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String valoracion;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Integer estrellas;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;
}

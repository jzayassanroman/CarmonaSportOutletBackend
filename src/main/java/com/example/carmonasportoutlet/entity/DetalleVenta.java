package com.example.carmonasportoutlet.entity;

import com.example.carmonasportoutlet.entity.Pedido;
import com.example.carmonasportoutlet.entity.Producto;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "detalle_venta", schema = "carmonasportoutlet")
public class DetalleVenta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer cantidad;

    @Column(nullable = false)
    private Float subtotal;

    @ManyToOne
    @JoinColumn(name = "id_pedido", nullable = false)
    private Pedido pedido;

    @ManyToOne
    @JoinColumn(name = "id_producto", nullable = false)
    private Producto producto;
}

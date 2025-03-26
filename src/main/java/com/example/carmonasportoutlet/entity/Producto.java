package com.example.carmonasportoutlet.entity;

import com.example.carmonasportoutlet.enumerados.Entrega;
import com.example.carmonasportoutlet.enumerados.EstadoProducto;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Builder
@Table(name = "producto", schema = "carmonasportoutlet", catalog = "postgres")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String tipo;

    @Column(nullable = false, length = 150)
    private String descripcion;

    @Column(nullable = false)
    private Float precio;

    @Column(nullable = false, length = 300)
    private String imagen1;

    @Column(length = 300)
    private String imagen2;

    @Column(length = 300)
    private String imagen3;

    @Column(length = 300)
    private String imagen4;

    @Column(nullable = false, length = 100)
    private Entrega entrega;

    @Column(nullable = false, length = 50)
    private EstadoProducto estado;

    @Column(nullable = false, length = 50)
    private boolean disponible;

    @ManyToOne
    @JoinColumn(name = "id_cliente", nullable = false)
    private Cliente cliente;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL)
    private List<Favoritos> favoritos;
}

package com.example.carmonasportoutlet.entity;

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
@Table(name = "cliente", schema = "carmonasportoutlet")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String apellido;

    @Column(nullable = false, length = 100)
    private String email;

    @Column(nullable = false)
    private Integer telefono;

    @Column(nullable = false, length = 100)
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "id_usuario", nullable = false)
    private User usuario;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Pedido> pedidos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Producto> productos;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Valoraciones> valoraciones;
}

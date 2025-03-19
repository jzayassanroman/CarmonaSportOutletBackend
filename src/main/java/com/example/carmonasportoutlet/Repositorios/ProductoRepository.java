package com.example.carmonasportoutlet.Repositorios;

import com.example.carmonasportoutlet.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer> {
    // Consulta personalizada para obtener el id del cliente asociado al producto
    @Query("SELECT p.cliente.id FROM Producto p WHERE p.id = :productoId")
    Integer findClienteIdByProductoId(Integer productoId);
}

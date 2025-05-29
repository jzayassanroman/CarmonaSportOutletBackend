package com.example.carmonasportoutlet.repositorios;

import com.example.carmonasportoutlet.entity.Valoraciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ValoracionesRepository extends JpaRepository<Valoraciones, Integer> {
    List<Valoraciones> findByClienteValoradoId(Integer id);
    Optional<Valoraciones> findByCliente_IdAndProducto_Id(Integer clienteId, Integer productoId);
    List<Valoraciones> findByProducto_Id(Integer productoId);



}

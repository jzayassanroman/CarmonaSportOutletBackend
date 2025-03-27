package com.example.carmonasportoutlet.repositorios;

import com.example.carmonasportoutlet.entity.Valoraciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValoracionesRepository extends JpaRepository<Valoraciones, Integer> {
}

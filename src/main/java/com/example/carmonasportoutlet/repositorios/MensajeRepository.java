package com.example.carmonasportoutlet.repositorios;

import com.example.carmonasportoutlet.entity.Mensaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MensajeRepository extends JpaRepository<Mensaje, Integer> {
}

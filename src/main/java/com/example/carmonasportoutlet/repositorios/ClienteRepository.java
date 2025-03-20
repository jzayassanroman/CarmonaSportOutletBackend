package com.example.carmonasportoutlet.repositorios;

import com.example.carmonasportoutlet.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}

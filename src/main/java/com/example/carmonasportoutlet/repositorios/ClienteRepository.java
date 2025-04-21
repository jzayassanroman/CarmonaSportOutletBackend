package com.example.carmonasportoutlet.repositorios;

import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
    Optional<Cliente> findByEmail(String email);
    Optional<Cliente> findByUsuario(User usuario);
    Cliente findByUsuario_Id(Integer idUsuario);

}

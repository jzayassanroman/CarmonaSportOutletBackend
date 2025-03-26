package com.example.carmonasportoutlet.repositorios;



import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<User, Integer> {
    boolean existsByUsername(String username);
    Optional<User> findByUsername(String username);


}

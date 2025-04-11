package com.example.carmonasportoutlet.repositorios;

import com.example.carmonasportoutlet.entity.User;
import com.example.carmonasportoutlet.entity.Valoraciones;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {
}

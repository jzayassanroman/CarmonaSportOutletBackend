package com.example.carmonasportoutlet.repositorios;



import com.example.carmonasportoutlet.entity.Favoritos;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FavoritosRepository extends JpaRepository<Favoritos, Integer> {

}

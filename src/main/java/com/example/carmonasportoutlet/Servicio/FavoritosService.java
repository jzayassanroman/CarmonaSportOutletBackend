package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.DTO.FavoritosDTO;
import com.example.carmonasportoutlet.entity.Favoritos;
import com.example.carmonasportoutlet.repositorios.FavoritosRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FavoritosService {

    private final FavoritosRepository favoritosRepository;

    public FavoritosService(FavoritosRepository favoritosRepository) {
        this.favoritosRepository = favoritosRepository;
    }

    public Favoritos agregarFavorito(Favoritos favorito) {
        return favoritosRepository.save(favorito);
    }

    public List<FavoritosDTO> listarFavoritos() {
        List<Favoritos> favoritos = favoritosRepository.findAll();
        return favoritos.stream()
                .map(fav -> new FavoritosDTO(
                        fav.getId(),
                        fav.getUsuario().getId(),
                        fav.getProducto().getId()
                ))
                .collect(Collectors.toList());
    }

    public Optional<Favoritos> obtenerFavoritoPorId(Integer id) {
        return favoritosRepository.findById(id);
    }

    public void eliminarFavorito(Integer id) {
        favoritosRepository.deleteById(id);
    }
}

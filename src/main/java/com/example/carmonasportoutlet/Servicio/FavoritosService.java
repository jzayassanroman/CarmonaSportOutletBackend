package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.DTO.FavoritosDTO;
import com.example.carmonasportoutlet.entity.Favoritos;
import com.example.carmonasportoutlet.repositorios.FavoritosRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class FavoritosService {

    private  FavoritosRepository favoritosRepository;


    public FavoritosDTO agregarFavorito(Favoritos favorito) {
        Favoritos nuevoFavorito = favoritosRepository.save(favorito);
        return new FavoritosDTO(nuevoFavorito.getId(),
                nuevoFavorito.getUsuario().getId(),
                nuevoFavorito.getProducto().getId());
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

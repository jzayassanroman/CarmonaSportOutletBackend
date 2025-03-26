package com.example.carmonasportoutlet.controladores;

import com.example.carmonasportoutlet.DTO.FavoritosDTO;
import com.example.carmonasportoutlet.entity.Favoritos;
import com.example.carmonasportoutlet.Servicio.FavoritosService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/favoritos")
public class FavoritosController {

    private final FavoritosService favoritosService;

    public FavoritosController(FavoritosService favoritosService) {
        this.favoritosService = favoritosService;
    }

    @PostMapping
    public ResponseEntity<Favoritos> agregarFavorito(@RequestBody Favoritos favorito) {
        return ResponseEntity.ok(favoritosService.agregarFavorito(favorito));
    }

    @GetMapping
    public ResponseEntity<List<FavoritosDTO>> listarFavoritos() {
        return ResponseEntity.ok(favoritosService.listarFavoritos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favoritos> obtenerFavorito(@PathVariable Integer id) {
        Optional<Favoritos> favorito = favoritosService.obtenerFavoritoPorId(id);
        return favorito.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarFavorito(@PathVariable Integer id) {
        favoritosService.eliminarFavorito(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.carmonasportoutlet.controladores;

import com.example.carmonasportoutlet.DTO.FavoritosDTO;
import com.example.carmonasportoutlet.entity.Favoritos;
import com.example.carmonasportoutlet.Servicio.FavoritosService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/favoritos")
public class FavoritosController {

    private  FavoritosService favoritosService;


    @PostMapping
    @RequestMapping("/crear")
    public ResponseEntity<FavoritosDTO> agregarFavorito(@RequestBody Favoritos favorito) {
        FavoritosDTO favoritoGuardado = favoritosService.agregarFavorito(favorito);
        return ResponseEntity.ok(favoritoGuardado);
    }


    @GetMapping("/all")
    public ResponseEntity<List<FavoritosDTO>> listarFavoritos() {
        return ResponseEntity.ok(favoritosService.listarFavoritos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Favoritos> obtenerFavorito(@PathVariable Integer id) {
        Optional<Favoritos> favorito = favoritosService.obtenerFavoritoPorId(id);
        return favorito.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarFavorito(@PathVariable Integer id) {
        favoritosService.eliminarFavorito(id);
        return ResponseEntity.noContent().build();
    }
}

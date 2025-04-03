package com.example.carmonasportoutlet.controladores;

import com.example.carmonasportoutlet.DTO.ValoracionesDTO;
import com.example.carmonasportoutlet.entity.Valoraciones;
import com.example.carmonasportoutlet.Servicio.ValoracionesService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/valoraciones")
public class ValoracionesController {

    private ValoracionesService valoracionService;

    @GetMapping("/all")
    public ResponseEntity<List<ValoracionesDTO>> listarValoraciones() {
        return ResponseEntity.ok(valoracionService.listarValoraciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValoracionesDTO> obtenerValoracionPorId(@PathVariable Integer id) {
        ValoracionesDTO valoracion = valoracionService.obtenerValoracionPorId(id);
        return valoracion != null ? ResponseEntity.ok(valoracion) : ResponseEntity.notFound().build();
    }

    @PostMapping("/crear")
    public ResponseEntity<ValoracionesDTO> crearValoracion(@RequestBody Valoraciones valoracion) {
        return ResponseEntity.ok(valoracionService.crearValoracion(valoracion));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<ValoracionesDTO> actualizarValoracion(@PathVariable Integer id, @RequestBody Valoraciones valoracion) {
        ValoracionesDTO valoracionActualizada = valoracionService.actualizarValoracion(id, valoracion);
        return valoracionActualizada != null ? ResponseEntity.ok(valoracionActualizada) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarValoracion(@PathVariable Integer id) {
        return valoracionService.eliminarValoracion(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

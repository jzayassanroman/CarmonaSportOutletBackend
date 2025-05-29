package com.example.carmonasportoutlet.controladores;

import com.example.carmonasportoutlet.dto.ValoracionesDTO;
import com.example.carmonasportoutlet.dto.ValoracionesRespuestaDTO;
import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.entity.Producto;
import com.example.carmonasportoutlet.entity.Valoraciones;
import com.example.carmonasportoutlet.Servicio.ValoracionesService;
import com.example.carmonasportoutlet.repositorios.ClienteRepository;
import com.example.carmonasportoutlet.repositorios.ProductoRepository;
import com.example.carmonasportoutlet.repositorios.ValoracionesRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/valoraciones")
public class ValoracionesController {

    private ValoracionesService valoracionService;
    private ValoracionesRepository valoracionesRepository;
    private ClienteRepository clienteRepository;
    private ProductoRepository productoRepository;

    @GetMapping("/all")
    public ResponseEntity<List<ValoracionesDTO>> listarValoraciones() {
        return ResponseEntity.ok(valoracionService.listarValoraciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ValoracionesDTO> obtenerValoracionPorId(@PathVariable Integer id) {
        ValoracionesDTO valoracion = valoracionService.obtenerValoracionPorId(id);
        return valoracion != null ? ResponseEntity.ok(valoracion) : ResponseEntity.notFound().build();
    }
    @GetMapping("/producto/{productoId}")
    public List<ValoracionesRespuestaDTO> getValoracionesDeProducto(@PathVariable Integer productoId) {
        List<Valoraciones> valoraciones = valoracionService.obtenerValoracionesDeProducto(productoId);
        return valoraciones.stream()
                .map(ValoracionesRespuestaDTO::new)
                .collect(Collectors.toList());
    }


    @PostMapping("/crear")
    public ResponseEntity<Void> crearValoracion(@RequestBody ValoracionesDTO request) {
        try {
            valoracionService.crearValoracion(request);
            return ResponseEntity.ok().build(); // 200 OK sin cuerpo
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping("/cliente/{id}")
    public List<Valoraciones> obtenerValoracionesPorCliente(@PathVariable Integer id) {
        return valoracionesRepository.findByClienteValoradoId(id);
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

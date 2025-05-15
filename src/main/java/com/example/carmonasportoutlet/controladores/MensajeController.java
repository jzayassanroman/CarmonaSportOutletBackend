package com.example.carmonasportoutlet.controladores;

import com.example.carmonasportoutlet.DTO.MensajeDTO;
import com.example.carmonasportoutlet.Servicio.MensajeService;
import com.example.carmonasportoutlet.entity.Mensaje;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/mensajes")
public class MensajeController {

    private MensajeService mensajeService;

    @GetMapping("/all")
    public ResponseEntity<List<MensajeDTO>> listarMensajes() {
        return ResponseEntity.ok(mensajeService.listarMensajes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<MensajeDTO> obtenerMensajePorId(@PathVariable Integer id) {
        MensajeDTO mensaje = mensajeService.obtenerMensajePorId(id);
        return mensaje != null ? ResponseEntity.ok(mensaje) : ResponseEntity.notFound().build();
    }

    @PostMapping("/crear")
    public ResponseEntity<MensajeDTO> crearMensaje(@RequestBody Mensaje mensaje) {
        return ResponseEntity.ok(mensajeService.crearMensaje(mensaje));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarMensaje(@PathVariable Integer id) {
        return mensajeService.eliminarMensaje(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

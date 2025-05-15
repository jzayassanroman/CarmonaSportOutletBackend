package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.DTO.MensajeDTO;
import com.example.carmonasportoutlet.entity.Mensaje;
import com.example.carmonasportoutlet.repositorios.MensajeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MensajeService {

    private final MensajeRepository mensajeRepository;

    public List<MensajeDTO> listarMensajes() {
        return mensajeRepository.findAll().stream()
                .map(mensaje -> new MensajeDTO(
                        mensaje.getId(),
                        mensaje.getChat().getId(),
                        mensaje.getEmisor().getId(),
                        mensaje.getContenido(),
                        mensaje.getFechaenvio()
                ))
                .collect(Collectors.toList());
    }

    public MensajeDTO obtenerMensajePorId(Integer id) {
        Optional<Mensaje> mensaje = mensajeRepository.findById(id);
        return mensaje.map(m -> new MensajeDTO(
                m.getId(),
                m.getChat().getId(),
                m.getEmisor().getId(),
                m.getContenido(),
                m.getFechaenvio()
        )).orElse(null);
    }

    public MensajeDTO crearMensaje(Mensaje mensaje) {
        Mensaje nuevoMensaje = mensajeRepository.save(mensaje);
        return new MensajeDTO(
                nuevoMensaje.getId(),
                nuevoMensaje.getChat().getId(),
                nuevoMensaje.getEmisor().getId(),
                nuevoMensaje.getContenido(),
                nuevoMensaje.getFechaenvio()
        );
    }

    public boolean eliminarMensaje(Integer id) {
        if (mensajeRepository.existsById(id)) {
            mensajeRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

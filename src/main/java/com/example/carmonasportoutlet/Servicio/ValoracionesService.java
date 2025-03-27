package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.DTO.ValoracionesDTO;
import com.example.carmonasportoutlet.entity.Valoraciones;
import com.example.carmonasportoutlet.repositorios.ValoracionesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ValoracionesService {

    private  ValoracionesRepository valoracionRepository;



    // 🔹 Listar todas las valoraciones
    public List<ValoracionesDTO> listarValoraciones() {
        return valoracionRepository.findAll()
                .stream()
                .map(valoracion -> new ValoracionesDTO(
                        valoracion.getId(),
                        valoracion.getValoracion(),
                        valoracion.getFecha(),
                        valoracion.getEstrellas(),
                        valoracion.getCliente().getId(),
                        valoracion.getClienteValorado().getId()
                ))
                .collect(Collectors.toList());
    }

    // 🔹 Buscar valoracion por ID
    public ValoracionesDTO obtenerValoracionPorId(Integer id) {
        Optional<Valoraciones> valoracion = valoracionRepository.findById(id);
        return valoracion.map(v -> new ValoracionesDTO(
                v.getId(),
                v.getValoracion(),
                v.getFecha(),
                v.getEstrellas(),
                v.getCliente().getId(),
                v.getClienteValorado().getId()
        )).orElse(null);
    }

    // 🔹 Crear una nueva valoración
    public ValoracionesDTO crearValoracion(Valoraciones valoracion) {
        Valoraciones nuevaValoracion = valoracionRepository.save(valoracion);
        return new ValoracionesDTO(
                nuevaValoracion.getId(),
                nuevaValoracion.getValoracion(),
                nuevaValoracion.getFecha(),
                nuevaValoracion.getEstrellas(),
                nuevaValoracion.getCliente().getId(),
                nuevaValoracion.getClienteValorado().getId()
        );
    }

    // 🔹 Editar una valoración
    public ValoracionesDTO actualizarValoracion(Integer id, Valoraciones valoracionActualizada) {
        return valoracionRepository.findById(id).map(valoracion -> {
            valoracion.setValoracion(valoracionActualizada.getValoracion());
            valoracion.setFecha(valoracionActualizada.getFecha());
            valoracion.setEstrellas(valoracionActualizada.getEstrellas());
            valoracion.setCliente(valoracionActualizada.getCliente());
            valoracion.setClienteValorado(valoracionActualizada.getClienteValorado());

            Valoraciones valoracionGuardada = valoracionRepository.save(valoracion);
            return new ValoracionesDTO(
                    valoracionGuardada.getId(),
                    valoracionGuardada.getValoracion(),
                    valoracionGuardada.getFecha(),
                    valoracionGuardada.getEstrellas(),
                    valoracionGuardada.getCliente().getId(),
                    valoracionGuardada.getClienteValorado().getId()
            );
        }).orElse(null);
    }

    // 🔹 Eliminar una valoración
    public boolean eliminarValoracion(Integer id) {
        if (valoracionRepository.existsById(id)) {
            valoracionRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

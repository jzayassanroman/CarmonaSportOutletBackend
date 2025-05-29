package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.dto.ValoracionesDTO;
import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.entity.Producto;
import com.example.carmonasportoutlet.entity.Valoraciones;
import com.example.carmonasportoutlet.repositorios.ClienteRepository;
import com.example.carmonasportoutlet.repositorios.ProductoRepository;
import com.example.carmonasportoutlet.repositorios.ValoracionesRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@AllArgsConstructor
public class ValoracionesService {

    private  ValoracionesRepository valoracionRepository;
    private ProductoRepository productoRepository;
    private ClienteRepository clienteRepository;



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


    public String crearValoracion(ValoracionesDTO request) {
        // Validate input IDs
        if (request.getIdCliente() == null) {
            throw new IllegalArgumentException("El ID del cliente que crea la valoración no puede ser nulo");
        }
        if (request.getIdClienteValorado() == null) {
            throw new IllegalArgumentException("El ID del cliente que recibe la valoración no puede ser nulo");
        }
        if (request.getIdProducto() == null) {
            throw new IllegalArgumentException("El ID del producto no puede ser nulo");
        }

        // Retrieve entities
        Cliente cliente = clienteRepository.findById(request.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente que crea la valoración no encontrado"));
        Cliente clienteValorado = clienteRepository.findById(request.getIdClienteValorado())
                .orElseThrow(() -> new RuntimeException("Cliente que recibe la valoración no encontrado"));
        Producto producto = productoRepository.findById(request.getIdProducto())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Create and save the Valoraciones entity
        Valoraciones valoracion = new Valoraciones();
        valoracion.setCliente(cliente);
        valoracion.setClienteValorado(clienteValorado);
        valoracion.setProducto(producto);
        valoracion.setEstrellas(request.getEstrellas());
        valoracion.setValoracion(request.getValoracion());
        valoracion.setFecha(LocalDateTime.now());

        valoracionRepository.save(valoracion);

        return "Valoración creada exitosamente";
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
    public List<ValoracionesDTO> obtenerResenasPorClienteValorado(Integer clienteValoradoId) {
        List<Valoraciones> valoraciones = valoracionRepository.findByClienteValoradoId(clienteValoradoId);
        return valoraciones.stream()
                .map(v -> new ValoracionesDTO(
                        v.getId(),
                        v.getValoracion(),
                        v.getFecha(),
                        v.getEstrellas(),
                        v.getCliente().getId(),
                        v.getClienteValorado().getId()
                )).collect(Collectors.toList());
    }


    public List<Valoraciones> obtenerValoracionesDeProducto(Integer productoId) {
        return valoracionRepository.findByProducto_Id(productoId);
    }

    // 🔹 Crear una nueva valoración
    public ValoracionesDTO crearValoracion(Valoraciones valoracion) {
        // Supongamos que en valoracion tienes el producto con su id
        Producto producto = productoRepository.findById(valoracion.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Asignamos el cliente que creó el producto como clienteValorado
        valoracion.setClienteValorado(producto.getCliente());

        // Guardamos la valoración con el clienteValorado asignado
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

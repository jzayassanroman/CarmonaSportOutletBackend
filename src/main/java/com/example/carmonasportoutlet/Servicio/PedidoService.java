package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.DTO.PedidoDTO;
import com.example.carmonasportoutlet.entity.Pedido;
import com.example.carmonasportoutlet.repositorios.PedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PedidoService {

    private PedidoRepository pedidoRepository;



    // 🔹 Listar todos los pedidos
    public List<PedidoDTO> listarPedidos() {
        return pedidoRepository.findAll()
                .stream()
                .map(pedido -> new PedidoDTO(
                        pedido.getId(),
                        pedido.getTotal(),
                        pedido.getEstado(),
                        pedido.getFecha(),
                        pedido.getCliente().getId(),
                        pedido.getProducto().getId()
                ))
                .collect(Collectors.toList());
    }

    // 🔹 Buscar pedido por ID
    public PedidoDTO obtenerPedidoPorId(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.map(p -> new PedidoDTO(
                p.getId(),
                p.getTotal(),
                p.getEstado(),
                p.getFecha(),
                p.getCliente().getId(),
                p.getProducto().getId()
        )).orElse(null);
    }

    // 🔹 Crear un nuevo pedido
    public PedidoDTO crearPedido(Pedido pedido) {
        Pedido nuevoPedido = pedidoRepository.save(pedido);
        return new PedidoDTO(
                nuevoPedido.getId(),
                nuevoPedido.getTotal(),
                nuevoPedido.getEstado(),
                nuevoPedido.getFecha(),
                nuevoPedido.getCliente().getId(),
                nuevoPedido.getProducto().getId()
        );
    }

    // 🔹 Editar un pedido
    public PedidoDTO actualizarPedido(Integer id, Pedido pedidoActualizado) {
        return pedidoRepository.findById(id).map(pedido -> {
            pedido.setTotal(pedidoActualizado.getTotal());
            pedido.setEstado(pedidoActualizado.getEstado());
            pedido.setFecha(pedidoActualizado.getFecha());
            pedido.setCliente(pedidoActualizado.getCliente());
            pedido.setProducto(pedidoActualizado.getProducto());

            Pedido pedidoGuardado = pedidoRepository.save(pedido);
            return new PedidoDTO(
                    pedidoGuardado.getId(),
                    pedidoGuardado.getTotal(),
                    pedidoGuardado.getEstado(),
                    pedidoGuardado.getFecha(),
                    pedidoGuardado.getCliente().getId(),
                    pedidoGuardado.getProducto().getId()
            );
        }).orElse(null);
    }

    // 🔹 Eliminar un pedido
    public boolean eliminarPedido(Integer id) {
        if (pedidoRepository.existsById(id)) {
            pedidoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}

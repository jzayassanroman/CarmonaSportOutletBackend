package com.example.carmonasportoutlet.controladores;

import com.example.carmonasportoutlet.DTO.PedidoDTO;
import com.example.carmonasportoutlet.entity.Pedido;
import com.example.carmonasportoutlet.Servicio.PedidoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/pedidos")
public class PedidoController {

    private  PedidoService pedidoService;


    @GetMapping("/all")
    public ResponseEntity<List<PedidoDTO>> listarPedidos() {
        return ResponseEntity.ok(pedidoService.listarPedidos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtenerPedidoPorId(@PathVariable Integer id) {
        PedidoDTO pedido = pedidoService.obtenerPedidoPorId(id);
        return pedido != null ? ResponseEntity.ok(pedido) : ResponseEntity.notFound().build();
    }

    @PostMapping("/crear")
    public ResponseEntity<PedidoDTO> crearPedido(@RequestBody Pedido pedido) {
        return ResponseEntity.ok(pedidoService.crearPedido(pedido));
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<PedidoDTO> actualizarPedido(@PathVariable Integer id, @RequestBody Pedido pedido) {
        PedidoDTO pedidoActualizado = pedidoService.actualizarPedido(id, pedido);
        return pedidoActualizado != null ? ResponseEntity.ok(pedidoActualizado) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarPedido(@PathVariable Integer id) {
        return pedidoService.eliminarPedido(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

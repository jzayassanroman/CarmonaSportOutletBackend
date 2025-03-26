package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.Repositorios.ProductoRepository;
import com.example.carmonasportoutlet.Repositorios.ClienteRepository;
import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.entity.Producto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProductoService {

    private ProductoRepository productoRepository;
    private ClienteRepository clienteRepository;

    public Producto crearProducto(Producto producto) {
        // Obtener el id del cliente asociado al producto
        Integer clienteId = productoRepository.findClienteIdByProductoId(producto.getId());

        // Obtener cliente desde la base de datos por el id
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        // Crear producto con el cliente asociado
        Producto nuevoProducto = new Producto();
        nuevoProducto.setNombre(producto.getNombre());
        nuevoProducto.setTipo(producto.getTipo());
        nuevoProducto.setDescripcion(producto.getDescripcion());
        nuevoProducto.setPrecio(producto.getPrecio());
        nuevoProducto.setImagen1(producto.getImagen1());
        nuevoProducto.setImagen2(producto.getImagen2());
        nuevoProducto.setImagen3(producto.getImagen3());
        nuevoProducto.setImagen4(producto.getImagen4());
        nuevoProducto.setEntrega(producto.getEntrega());
        nuevoProducto.setEstado(producto.getEstado());
        nuevoProducto.setDisponible(producto.isDisponible());
        nuevoProducto.setCliente(cliente);  // Asociamos el cliente al producto

// Guardar el producto en la base de datos
        return productoRepository.save(nuevoProducto);
    }
}

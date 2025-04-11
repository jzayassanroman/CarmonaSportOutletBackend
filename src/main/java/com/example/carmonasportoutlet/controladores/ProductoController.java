package com.example.carmonasportoutlet.controladores;


import com.example.carmonasportoutlet.dto.ProductoClienteDTO;
import com.example.carmonasportoutlet.dto.ProductoDTO;
import com.example.carmonasportoutlet.entity.Producto;
import com.example.carmonasportoutlet.Servicio.ProductoServicio;
import com.example.carmonasportoutlet.repositorios.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoServicio productoServicio;
    @Autowired
    private final ProductoRepository productoRepository;

    public ProductoController(ProductoServicio productoServicio, ProductoRepository productoRepository) {
        this.productoServicio = productoServicio;
        this.productoRepository = productoRepository;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productos = productoServicio.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping("/{id}")
    public Optional<Producto> getProductoById(@PathVariable Integer id) {
        return productoRepository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoDTO productoDTO) {
        Producto producto = productoServicio.crearProducto(productoDTO);
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Producto> editarProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoDTO) {
        Producto producto = productoServicio.editarProducto(id, productoDTO);
        return ResponseEntity.ok(producto);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Integer id) {
        productoServicio.eliminarProducto(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ProductoClienteDTO>> getProductosByCliente(@PathVariable Integer clienteId) {
        List<ProductoClienteDTO> productos = productoServicio.obtenerProductosPorCliente(clienteId);
        return ResponseEntity.ok(productos);
    }





}

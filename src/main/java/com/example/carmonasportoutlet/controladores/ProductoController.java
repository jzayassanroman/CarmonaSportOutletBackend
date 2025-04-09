package com.example.carmonasportoutlet.controladores;


import com.example.carmonasportoutlet.DTO.ProductoClienteDTO;
import com.example.carmonasportoutlet.DTO.ProductoDTO;
import com.example.carmonasportoutlet.entity.Producto;
import com.example.carmonasportoutlet.Servicio.ProductoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoServicio productoServicio;

    public ProductoController(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ProductoDTO>> getAllProductos() {
        List<ProductoDTO> productos = productoServicio.getAllProductos();
        return ResponseEntity.ok(productos);
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoDTO productoDTO) {
        Producto producto = productoServicio.crearProducto(productoDTO);
        return ResponseEntity.ok(producto);
    }

    @PutMapping("/editar/{id}")
    public ResponseEntity<Void> editarProducto(@PathVariable Integer id, @RequestBody ProductoDTO productoDTO) {
        productoServicio.editarProducto(id, productoDTO);
        return ResponseEntity.ok().build();
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

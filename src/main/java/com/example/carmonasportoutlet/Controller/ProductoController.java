package com.example.carmonasportoutlet.Controller;

import com.example.carmonasportoutlet.Services.ProductoService;
import com.example.carmonasportoutlet.entity.Producto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/productos")
@AllArgsConstructor
public class ProductoController {
    @Autowired
    private ProductoService productoService;

    // Endpoint para crear un nuevo producto
    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto productos) {
        try {
            Producto producto = productoService.crearProducto(productos);  // Llamamos al servicio para crear el producto
            return new ResponseEntity<>(producto, HttpStatus.CREATED);  // Respondemos con el producto creado
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);  // Si el cliente no se encuentra, respondemos con un 404
        }
    }
}

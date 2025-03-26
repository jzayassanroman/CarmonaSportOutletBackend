package com.example.carmonasportoutlet.controladores;

import com.example.carmonasportoutlet.Servicio.ProductoService;
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

    public ProductoController() {
        System.out.println("ProductoController cargado");
    }

    @PostMapping("/crear")
    public ResponseEntity<Producto> crearProducto(@RequestBody Producto productos) {
        try {
            Producto producto = productoService.crearProducto(productos);
            return new ResponseEntity<>(producto, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

package com.example.carmonasportoutlet.controladores;


import com.example.carmonasportoutlet.dto.ProductoDTO;
import com.example.carmonasportoutlet.entity.Producto;
import com.example.carmonasportoutlet.Servicio.ProductoServicio;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {
    private final ProductoServicio productoServicio;

    public ProductoController(ProductoServicio productoServicio) {
        this.productoServicio = productoServicio;
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoDTO productoDTO) {
        Producto producto = productoServicio.crearProducto(productoDTO);
        return ResponseEntity.ok(producto);
    }
}

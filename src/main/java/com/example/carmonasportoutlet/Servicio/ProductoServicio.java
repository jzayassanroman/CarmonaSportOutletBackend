package com.example.carmonasportoutlet.Servicio;


import com.example.carmonasportoutlet.dto.ProductoDTO;
import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.entity.Producto;
import com.example.carmonasportoutlet.repositorios.ClienteRepository;
import com.example.carmonasportoutlet.repositorios.ProductoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProductoServicio {
    private  ProductoRepository productoRepository;
    private  ClienteRepository clienteRepository;

    public ProductoServicio(ProductoRepository productoRepository, ClienteRepository clienteRepository) {
        this.productoRepository = productoRepository;
        this.clienteRepository = clienteRepository;
    }

    public Producto crearProducto(ProductoDTO dto) {
        Cliente cliente = clienteRepository.findById(dto.getIdCliente())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        Producto producto = Producto.builder()
                .nombre(dto.getNombre())
                .tipo(dto.getTipo())
                .descripcion(dto.getDescripcion())
                .precio(dto.getPrecio())
                .imagen1(dto.getImagen1())
                .imagen2(dto.getImagen2())
                .imagen3(dto.getImagen3())
                .imagen4(dto.getImagen4())
                .entrega(dto.getEntrega())
                .estado(dto.getEstado())
                .disponible(dto.isDisponible())
                .cliente(cliente)
                .build();

        return productoRepository.save(producto);
    }
}

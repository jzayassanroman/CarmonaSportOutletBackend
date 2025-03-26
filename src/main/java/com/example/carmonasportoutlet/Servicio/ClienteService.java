package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.repositorios.ClienteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarClientes() {
        return clienteRepository.findAll();
    }

    public Optional<Cliente> obtenerClientePorId(Integer id) {
        return clienteRepository.findById(id);
    }

    public Cliente editarCliente(Integer id, Cliente clienteActualizado) {
        return clienteRepository.findById(id)
                .map(cliente -> {
                    cliente.setNombre(clienteActualizado.getNombre());
                    cliente.setApellido(clienteActualizado.getApellido());
                    cliente.setEmail(clienteActualizado.getEmail());
                    cliente.setTelefono(clienteActualizado.getTelefono());
                    cliente.setDireccion(clienteActualizado.getDireccion());
                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    public void eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }
}

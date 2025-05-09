package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.dto.ClienteEditarDTO;
import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.repositorios.ClienteRepository;
import com.example.carmonasportoutlet.repositorios.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ClienteService {

    private ClienteRepository clienteRepository;
    private UsuarioRepository usuarioRepository;


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

                    // 🆕 Editar el username si se ha enviado uno nuevo
                    if (cliente.getUsuario() != null && clienteActualizado.getUsuario() != null) {
                        cliente.getUsuario().setUsername(clienteActualizado.getUsuario().getUsername());
                        usuarioRepository.save(cliente.getUsuario());
                    }

                    return clienteRepository.save(cliente);
                })
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }


    public void eliminarCliente(Integer id) {
        clienteRepository.deleteById(id);
    }

    public Cliente editarPerfil(Integer clienteId, ClienteEditarDTO clienteEditarDTO) {
        Optional<Cliente> clienteOpt = clienteRepository.findById(clienteId);
        if (clienteOpt.isPresent()) {
            Cliente cliente = clienteOpt.get();

            Integer telefono = null;
            try {
                telefono = Integer.parseInt(clienteEditarDTO.getTelefono());
            } catch (NumberFormatException e) {
                // Manejo de error
            }

            cliente.setNombre(clienteEditarDTO.getNombre());
            cliente.setApellido(clienteEditarDTO.getApellido());
            cliente.setEmail(clienteEditarDTO.getEmail());
            cliente.setTelefono(telefono);
            cliente.setDireccion(clienteEditarDTO.getDireccion());

            // ✅ Convertimos el enum a número (ordinal)
            if (clienteEditarDTO.getProvincia() != null) {
                cliente.setProvincia(clienteEditarDTO.getProvincia().ordinal());
            }

            return clienteRepository.save(cliente);
        }
        return null;
    }




}

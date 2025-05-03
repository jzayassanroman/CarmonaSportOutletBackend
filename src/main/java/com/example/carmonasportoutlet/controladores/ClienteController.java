package com.example.carmonasportoutlet.controladores;

import com.example.carmonasportoutlet.dto.ClienteEditarDTO;
import com.example.carmonasportoutlet.dto.ClientePerfilDTO;
import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.Servicio.ClienteService;
import com.example.carmonasportoutlet.enumerados.Provincia;
import com.example.carmonasportoutlet.security.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/clientes")
public class ClienteController {

    private  ClienteService clienteService;
    private final JwtService jwtService;

    @PostMapping("/crear")
    public ResponseEntity<Cliente> crearCliente(@RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.crearCliente(cliente));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Cliente>> listarClientes() {
        return ResponseEntity.ok(clienteService.listarClientes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerCliente(@PathVariable Integer id) {
        Optional<Cliente> cliente = clienteService.obtenerClientePorId(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/perfil")
    public ResponseEntity<ClientePerfilDTO> obtenerPerfilCliente(@RequestHeader("Authorization") String token) {
        Integer clienteId = jwtService.extractClaim(token.replace("Bearer ", ""), claims -> claims.get("clienteId", Integer.class));

        return clienteService.obtenerClientePorId(clienteId)
                .map(cliente -> {
                    String telefono = cliente.getTelefono() != null ? cliente.getTelefono().toString() : null;

                    // Lanza excepción si el índice es inválido
                    Provincia provinciaEnum = Provincia.values()[cliente.getProvincia()];

                    ClientePerfilDTO perfilDTO = new ClientePerfilDTO(
                            cliente.getId(), // ⬅️ Incluye el ID
                            cliente.getNombre(),
                            cliente.getApellido(),
                            cliente.getEmail(),
                            telefono,
                            cliente.getDireccion(),
                            provinciaEnum
                    );
                    return ResponseEntity.ok(perfilDTO);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }





    @PutMapping("/perfil/{id}")
    public ResponseEntity<ClientePerfilDTO> editarPerfilCliente(
            @PathVariable("id") Integer id,
            @RequestBody ClienteEditarDTO clienteEditarDTO,
            @RequestHeader("Authorization") String token) {

        Integer clienteId = jwtService.extractClaim(token.replace("Bearer ", ""), claims -> claims.get("clienteId", Integer.class));

        if (!clienteId.equals(id)) {
            return ResponseEntity.status(403).build();
        }

        Cliente clienteActualizado = clienteService.editarPerfil(id, clienteEditarDTO);

        if (clienteActualizado != null) {
            String telefono = String.valueOf(clienteActualizado.getTelefono());

            // Lanza excepción si el índice es inválido
            Provincia provinciaEnum = Provincia.values()[clienteActualizado.getProvincia()];

            ClientePerfilDTO perfilDTO = new ClientePerfilDTO(
                    clienteActualizado.getId(), // ⬅️ Incluye el ID
                    clienteActualizado.getNombre(),
                    clienteActualizado.getApellido(),
                    clienteActualizado.getEmail(),
                    telefono,
                    clienteActualizado.getDireccion(),
                    provinciaEnum
            );
            return ResponseEntity.ok(perfilDTO);
        }

        return ResponseEntity.notFound().build();
    }




    @PutMapping("/editar/{id}")
    public ResponseEntity<Cliente> editarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        return ResponseEntity.ok(clienteService.editarCliente(id, cliente));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarCliente(@PathVariable Integer id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.carmonasportoutlet.controladores;
import com.example.carmonasportoutlet.DTO.UserDTO;
import com.example.carmonasportoutlet.Servicio.UserService;
import com.example.carmonasportoutlet.entity.User;
import com.example.carmonasportoutlet.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<List<UserDTO>> obtenerUsuarios() {
        List<UserDTO> usuarios = userService.obtenerUsuarios();
        return new ResponseEntity<>(usuarios, HttpStatus.OK);
    }
    @PutMapping("/ban/{id}")
    public ResponseEntity<Map<String, String>> banearUsuario(@PathVariable Integer id) {
        try {
            userService.banearUsuario(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario baneado correctamente.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(404).body(errorResponse);
        }
    }
    @PutMapping("/desbanear/{id}")
    public ResponseEntity<String> desbanearUsuario(@PathVariable Integer id) {
        User user = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        user.setIsBanned(false);
        usuarioRepository.save(user);

        return ResponseEntity.ok("Usuario desbaneado exitosamente");
    }

    @DeleteMapping("/eliminar/{id}")
//    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<Map<String, String>> eliminarUsuario(@PathVariable Integer id) {
        try {
            userService.eliminarUsuarioYCliente(id);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Usuario y cliente eliminados correctamente.");
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
    }
}

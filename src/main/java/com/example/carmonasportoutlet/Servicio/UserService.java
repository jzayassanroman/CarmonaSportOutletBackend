package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.dto.ClienteDTO;
import com.example.carmonasportoutlet.dto.UserDTO;
import com.example.carmonasportoutlet.email.EmailService;
import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.repositorios.ClienteRepository;
import com.example.carmonasportoutlet.security.RegistroRequest;
import com.example.carmonasportoutlet.entity.User;
import com.example.carmonasportoutlet.enumerados.Rol;
import com.example.carmonasportoutlet.repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserService {

    private  UsuarioRepository usuarioRepository;
    private  PasswordEncoder passwordEncoder;
    private  ClienteRepository clienteRepository;

    private EmailService emailService;

    @Transactional
    public String registerUser(RegistroRequest request) {
        if (usuarioRepository.existsByUsername(request.getUsername())) {
            return "El usuario ya existe";
        }

        User newUser = new User();
        newUser.setUsername(request.getUsername());
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setIsVerified(false);
        newUser.setVerificationToken(generateVerificationToken());
        newUser.setRol(Rol.USUARIO);

        usuarioRepository.save(newUser);

        // Crear y guardar el cliente asociado al usuario
        Cliente newCliente = new Cliente();
        newCliente.setNombre(request.getNombre());
        newCliente.setApellido(request.getApellido());
        newCliente.setTelefono(request.getTelefono());
        newCliente.setDireccion(request.getDireccion());
        newCliente.setEmail(request.getEmail());
        newCliente.setUsuario(newUser);  // Relacionar cliente con usuario

        clienteRepository.save(newCliente);


        emailService.sendVerificationEmail(newCliente.getEmail(), newUser.getVerificationToken());

        return "Usuario registrado exitosamente. Verificación pendiente.";
    }


    private String generateVerificationToken() {
        String letras = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder token = new StringBuilder();
        for (int i = 0; i < 5; i++) {
            int index = (int) (Math.random() * letras.length());
            token.append(letras.charAt(index));
        }
        return token.toString();
    }
    public void eliminarUsuarioYCliente(Integer idUsuario) {
        User user = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        Cliente cliente = clienteRepository.findByUsuario_Id(idUsuario);
        if (cliente != null) {
            clienteRepository.delete(cliente);
        }

        usuarioRepository.delete(user);
    }
    public List<UserDTO> obtenerUsuarios() {
        List<User> usuarios = usuarioRepository.findAll();

        return usuarios.stream().map(user -> {
            UserDTO dto = new UserDTO();
            dto.setId(user.getId());
            dto.setUsername(user.getUsername());
            dto.setRol(user.getRol().name());
            dto.setIsVerified(user.getIsVerified());

            if (user.getCliente() != null) {
                Cliente cliente = user.getCliente();
                ClienteDTO clienteDTO = new ClienteDTO();
                clienteDTO.setId(cliente.getId());
                clienteDTO.setNombre(cliente.getNombre());
                clienteDTO.setApellido(cliente.getApellido());
                clienteDTO.setEmail(cliente.getEmail());
                clienteDTO.setDireccion(cliente.getDireccion());
                clienteDTO.setTelefono(String.valueOf(cliente.getTelefono()));

                dto.setCliente(clienteDTO);
            }

            return dto;
        }).collect(Collectors.toList());
    }


}
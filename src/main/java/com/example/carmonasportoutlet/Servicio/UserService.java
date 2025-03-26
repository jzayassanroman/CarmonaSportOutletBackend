package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.email.EmailService;
import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.repositorios.ClienteRepository;
import com.example.carmonasportoutlet.security.RegistroRequest;
import com.example.carmonasportoutlet.entity.User;
import com.example.carmonasportoutlet.enumerados.Rol;
import com.example.carmonasportoutlet.repositorios.UsuarioRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

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

}
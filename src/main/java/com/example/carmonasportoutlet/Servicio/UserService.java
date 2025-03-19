package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.email.EmailService;
import com.example.carmonasportoutlet.security.RegistroRequest;
import com.example.carmonasportoutlet.entity.User;
import com.example.carmonasportoutlet.enumerados.Rol;
import com.example.carmonasportoutlet.repositorios.UsuarioRepository;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Builder
@Service
public class UserService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    public UserService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

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

        emailService.sendVerificationEmail(newUser.getUsername(), newUser.getVerificationToken());

        return "Usuario registrado exitosamente. Verificación pendiente.";
    }

    private String generateVerificationToken() {
        return UUID.randomUUID().toString();
    }
}
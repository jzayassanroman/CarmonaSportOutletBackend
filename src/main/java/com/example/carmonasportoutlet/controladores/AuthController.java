package com.example.carmonasportoutlet.controladores;

import com.example.carmonasportoutlet.Servicio.UserService;
import com.example.carmonasportoutlet.entity.User;
import com.example.carmonasportoutlet.repositorios.UsuarioRepository;
import com.example.carmonasportoutlet.security.JwtService;
import com.example.carmonasportoutlet.security.RegistroRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;
import com.example.carmonasportoutlet.security.VerificationRequest;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;
    private final UserService userService;
    private final UsuarioRepository usuarioRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtService jwtService,
                          UserDetailsService userDetailsService, UserService userService,
                          UsuarioRepository usuarioRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        UserDetails user = userDetailsService.loadUserByUsername(username);
        return jwtService.generateToken(user);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegistroRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestBody VerificationRequest request) {
        User user = usuarioRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (user.getVerificationToken().equals(request.getVerificationToken())) {
            user.setIsVerified(true);
            usuarioRepository.save(user);
            return ResponseEntity.ok("Cuenta verificada con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código incorrecto.");
        }
    }
}

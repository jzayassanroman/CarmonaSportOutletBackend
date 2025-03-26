package com.example.carmonasportoutlet.controladores;

import com.example.carmonasportoutlet.Servicio.UserService;
import com.example.carmonasportoutlet.entity.Cliente;
import com.example.carmonasportoutlet.entity.User;
import com.example.carmonasportoutlet.repositorios.ClienteRepository;
import com.example.carmonasportoutlet.repositorios.UsuarioRepository;
import com.example.carmonasportoutlet.security.JwtService;
import com.example.carmonasportoutlet.security.LoginRequest;
import com.example.carmonasportoutlet.security.RegistroRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import com.example.carmonasportoutlet.security.VerificationRequest;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthController {
    private AuthenticationManager authenticationManager;
    private  JwtService jwtService;
    private  UserDetailsService userDetailsService;
    private  UserService userService;
    private  UsuarioRepository usuarioRepository;
    private  ClienteRepository clienteRepository;



    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
            );
            UserDetails user = userDetailsService.loadUserByUsername(loginRequest.getUsername());
            String token = jwtService.generateToken(user);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Credenciales incorrectas");
        } catch (UsernameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Usuario no encontrado");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error en el login");
        }
    }



    @PostMapping("/register")
    public String register(@RequestBody RegistroRequest request) {
        return userService.registerUser(request);
    }

    @PostMapping("/verify")
    public ResponseEntity<String> verifyUser(@RequestBody VerificationRequest request) {
        Cliente cliente = clienteRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));

        User user = cliente.getUsuario();  // Obtener el usuario asociado al cliente
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuario no encontrado para este cliente.");
        }

        System.out.println("Código enviado al usuario: " + user.getVerificationToken());
        System.out.println("Código recibido en la solicitud: " + request.getVerificationToken());

        if (user.getVerificationToken() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado un código de verificación.");
        }

        // Convertimos ambos valores a mayúsculas y eliminamos espacios en blanco antes de compararlos
        if (user.getVerificationToken().trim().equalsIgnoreCase(request.getVerificationToken().trim())) {
            user.setIsVerified(true);
            usuarioRepository.save(user);
            return ResponseEntity.ok("Cuenta verificada con éxito.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Código incorrecto.");
        }
    }


}

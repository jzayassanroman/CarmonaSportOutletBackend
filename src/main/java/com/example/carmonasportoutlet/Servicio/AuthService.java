//package com.example.carmonasportoutlet.Servicio;
//
//import com.example.carmonasportoutlet.entity.Cliente;
//import com.example.carmonasportoutlet.entity.User;
//import com.example.carmonasportoutlet.repositorios.ClienteRepository;
//import com.example.carmonasportoutlet.repositorios.UsuarioRepository;
//import lombok.AllArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//@Service
//@AllArgsConstructor
//public class AuthService {
//    @Autowired
//    private UsuarioRepository usuarioRepository;
//
//    @Autowired
//    private ClienteRepository clienteRepository;
//
//    @Autowired
//    private JwtUtil jwtUtil;
//
//    public String registrarUsuario(String email, String password) {
//        if (usuarioRepository.findByClientesEmail(email).isPresent()) {
//            throw new RuntimeException("El email ya está en uso.");
//        }
//
//        User usuario = new User();
//        usuario.setUsername(email);
//        usuario.setPassword(password);  // Asegúrate de encriptar la contraseña
//        usuarioRepository.save(usuario);
//
//        // Generamos el token JWT
//        return jwtUtil.generateToken(usuario.getEmail());
//    }
//
//    public Cliente completarCliente(Integer usuarioId, Cliente clienteDatos) {
//        User usuario = usuarioRepository.findById(usuarioId)
//                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
//
//        Cliente cliente = new Cliente();
//        cliente.setNombre(clienteDatos.getNombre());
//        cliente.setTelefono(clienteDatos.getTelefono());
//        cliente.setDireccion(clienteDatos.getDireccion());
//        cliente.setUsuario(usuario);
//
//        usuario.setCliente(cliente);
//        usuario.setClienteCreado(true);
//        usuarioRepository.save(usuario);
//
//        return clienteRepository.save(cliente);
//    }
//}
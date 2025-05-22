package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.dto.ChatDTO;
import com.example.carmonasportoutlet.entity.Chat;
import com.example.carmonasportoutlet.entity.Producto;
import com.example.carmonasportoutlet.entity.User;
import com.example.carmonasportoutlet.repositorios.ChatRepository;
import com.example.carmonasportoutlet.repositorios.ProductoRepository;
import com.example.carmonasportoutlet.repositorios.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;
    private final UserRepository userRepository;
    private final ProductoRepository productoRepository;

    public List<ChatDTO> listarChats() {
        return chatRepository.findAll().stream()
                .map(chat -> new ChatDTO(
                        chat.getId(),
                        chat.getRemitente().getId(),
                        chat.getDestinatario().getId(),
                        chat.getProducto().getId(),
                        chat.getFechacreacion()
                ))
                .collect(Collectors.toList());
    }
    public List<ChatDTO> obtenerChatsDeUsuario(Integer usuarioId) {
        return chatRepository.findByRemitenteIdOrDestinatarioId(usuarioId, usuarioId).stream()
                .map(chat -> new ChatDTO(
                        chat.getId(),
                        chat.getRemitente().getId(),
                        chat.getDestinatario().getId(),
                        chat.getProducto().getId(),
                        chat.getFechacreacion()
                ))
                .collect(Collectors.toList());
    }


    public ChatDTO obtenerChatPorId(Integer id) {
        Optional<Chat> chat = chatRepository.findById(id);
        return chat.map(c -> new ChatDTO(
                c.getId(),
                c.getRemitente().getId(),
                c.getDestinatario().getId(),
                c.getProducto().getId(),
                c.getFechacreacion()
        )).orElse(null);
    }

    public ChatDTO crearChat(Chat chat) {
        if (chat.getRemitente() == null || chat.getRemitente().getId() == null) {
            throw new IllegalArgumentException("El remitente debe estar definido");
        }

        if (chat.getDestinatario() == null || chat.getDestinatario().getId() == null) {
            throw new IllegalArgumentException("El destinatario debe estar definido");
        }

        if (chat.getProducto() == null || chat.getProducto().getId() == null) {
            throw new IllegalArgumentException("El producto debe estar definido");
        }

        // Verificar si ya existe un chat con los mismos remitente, destinatario y producto
        Optional<Chat> chatExistente = chatRepository.findByRemitenteIdAndDestinatarioIdAndProductoId(
                chat.getRemitente().getId(),
                chat.getDestinatario().getId(),
                chat.getProducto().getId()
        );

        if (chatExistente.isPresent()) {
            // Si ya existe, devolver el chat existente
            Chat existente = chatExistente.get();
            return new ChatDTO(
                    existente.getId(),
                    existente.getRemitente().getId(),
                    existente.getDestinatario().getId(),
                    existente.getProducto().getId(),
                    existente.getFechacreacion()
            );
        }

        // Cargar entidades reales desde la base de datos
        User remitente = userRepository.findById(chat.getRemitente().getId())
                .orElseThrow(() -> new RuntimeException("Remitente no encontrado"));
        User destinatario = userRepository.findById(chat.getDestinatario().getId())
                .orElseThrow(() -> new RuntimeException("Destinatario no encontrado"));
        Producto producto = productoRepository.findById(chat.getProducto().getId())
                .orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Asignar entidades cargadas
        chat.setRemitente(remitente);
        chat.setDestinatario(destinatario);
        chat.setProducto(producto);
        chat.setFechacreacion(new Date());

        Chat nuevoChat = chatRepository.save(chat);

        return new ChatDTO(
                nuevoChat.getId(),
                nuevoChat.getRemitente().getId(),
                nuevoChat.getDestinatario().getId(),
                nuevoChat.getProducto().getId(),
                nuevoChat.getFechacreacion()
        );
    }

    public boolean eliminarChat(Integer id) {
        if (chatRepository.existsById(id)) {
            chatRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
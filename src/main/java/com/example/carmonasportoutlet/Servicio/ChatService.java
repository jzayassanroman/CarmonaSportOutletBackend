package com.example.carmonasportoutlet.Servicio;

import com.example.carmonasportoutlet.DTO.ChatDTO;
import com.example.carmonasportoutlet.entity.Chat;
import com.example.carmonasportoutlet.repositorios.ChatRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ChatService {

    private final ChatRepository chatRepository;

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

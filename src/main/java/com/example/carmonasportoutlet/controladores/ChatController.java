package com.example.carmonasportoutlet.controladores;

import com.example.carmonasportoutlet.DTO.ChatDTO;
import com.example.carmonasportoutlet.Servicio.ChatService;
import com.example.carmonasportoutlet.entity.Chat;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/chats")
public class ChatController {

    private ChatService chatService;

    @GetMapping("/all")
    public ResponseEntity<List<ChatDTO>> listarChats() {
        return ResponseEntity.ok(chatService.listarChats());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatDTO> obtenerChatPorId(@PathVariable Integer id) {
        ChatDTO chat = chatService.obtenerChatPorId(id);
        return chat != null ? ResponseEntity.ok(chat) : ResponseEntity.notFound().build();
    }

    @PostMapping("/crear")
    public ResponseEntity<ChatDTO> crearChat(@RequestBody Chat chat) {
        return ResponseEntity.ok(chatService.crearChat(chat));
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarChat(@PathVariable Integer id) {
        return chatService.eliminarChat(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }
}

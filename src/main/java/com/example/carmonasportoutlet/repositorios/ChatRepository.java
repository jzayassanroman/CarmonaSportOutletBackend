package com.example.carmonasportoutlet.repositorios;

import com.example.carmonasportoutlet.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChatRepository extends JpaRepository<Chat, Integer> {
    Optional<Chat> findByRemitenteIdAndDestinatarioIdAndProductoId(Integer remitenteId, Integer destinatarioId, Integer productoId);
    List<Chat> findByRemitenteIdOrDestinatarioId(Integer remitenteId, Integer destinatarioId);
}
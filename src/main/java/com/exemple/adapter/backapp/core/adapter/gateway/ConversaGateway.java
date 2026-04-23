package com.exemple.adapter.backapp.core.adapter.gateway;

import com.exemple.adapter.backapp.core.domain.Conversa;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ConversaGateway {
    Conversa criar(Conversa conversa);
    Optional<Conversa> buscarPorId(UUID idConversa);
    List<Conversa> buscarPorCliente(UUID idCliente);
    List<Conversa> buscarPorAdvogado(UUID idAdvogado);
    List<Conversa> buscarPorCaso(UUID idCaso);
    boolean existePorParticipantesECaso(UUID idCliente, UUID idAdvogado, UUID idCaso);
}

package com.exemple.adapter.backapp.core.adapter.gateway;

import com.exemple.adapter.backapp.core.domain.Conversa;

import java.util.List;
import java.util.UUID;

public interface ConversaGateway {
    Conversa criar(Conversa conversa);
    List<Conversa> buscarPorCliente(UUID idCliente);
    List<Conversa> buscarPorAdvogado(UUID idAdvogado);
}

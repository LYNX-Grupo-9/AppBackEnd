package com.exemple.adapter.backapp.core.application.usecase.conversa;

import com.exemple.adapter.backapp.core.adapter.gateway.ConversaGateway;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.ConversaResponse;
import com.exemple.adapter.backapp.core.domain.Conversa;

import java.util.NoSuchElementException;
import java.util.UUID;

public class BuscarConversaPorIdUseCase {

    private final ConversaGateway conversaGateway;

    public BuscarConversaPorIdUseCase(ConversaGateway conversaGateway) {
        this.conversaGateway = conversaGateway;
    }

    public ConversaResponse executar(UUID idConversa) {
        Conversa conversa = conversaGateway.buscarPorId(idConversa)
                .orElseThrow(() -> new NoSuchElementException("Conversa não encontrada"));

        return new ConversaResponse(
                conversa.getIdConversa(),
                conversa.getIdCliente(),
                conversa.getIdAdvogado(),
                conversa.getIdCaso(),
                conversa.getCriadoEm()
        );
    }
}

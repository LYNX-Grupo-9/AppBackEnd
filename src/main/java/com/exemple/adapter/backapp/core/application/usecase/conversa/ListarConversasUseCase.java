package com.exemple.adapter.backapp.core.application.usecase.conversa;

import com.exemple.adapter.backapp.core.adapter.gateway.ConversaGateway;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.ConversaResponse;

import java.util.List;
import java.util.UUID;

public class ListarConversasUseCase {

    private final ConversaGateway conversaGateway;

    public ListarConversasUseCase(ConversaGateway conversaGateway) {
        this.conversaGateway = conversaGateway;
    }

    public List<ConversaResponse> listarPorCliente(UUID idCliente) {
        return conversaGateway.buscarPorCliente(idCliente)
                .stream()
                .map(conversa -> new ConversaResponse(
                        conversa.getIdConversa(),
                        conversa.getIdCliente(),
                        conversa.getIdAdvogado(),
                        conversa.getIdCaso(),
                        conversa.getCriadoEm()
                ))
                .toList();
    }

    public List<ConversaResponse> listarPorAdvogado(UUID idAdvogado) {
        return conversaGateway.buscarPorAdvogado(idAdvogado)
                .stream()
                .map(conversa -> new ConversaResponse(
                        conversa.getIdConversa(),
                        conversa.getIdCliente(),
                        conversa.getIdAdvogado(),
                        conversa.getIdCaso(),
                        conversa.getCriadoEm()
                ))
                .toList();
    }
}

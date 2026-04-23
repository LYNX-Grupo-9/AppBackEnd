package com.exemple.adapter.backapp.core.application.usecase.conversa;

import com.exemple.adapter.backapp.core.adapter.gateway.ConversaGateway;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.ConversaResponse;

import java.util.List;
import java.util.UUID;

public class ListarConversasUseCase {

    private final ConversaGateway conversaGateway;
    private final ConversaResponseAssembler conversaResponseAssembler;

    public ListarConversasUseCase(ConversaGateway conversaGateway,
                                  ConversaResponseAssembler conversaResponseAssembler) {
        this.conversaGateway = conversaGateway;
        this.conversaResponseAssembler = conversaResponseAssembler;
    }

    public List<ConversaResponse> listarPorCliente(UUID idCliente) {
        return conversaGateway.buscarPorCliente(idCliente)
                .stream()
                .map(conversaResponseAssembler::montar)
                .toList();
    }

    public List<ConversaResponse> listarPorAdvogado(UUID idAdvogado) {
        return conversaGateway.buscarPorAdvogado(idAdvogado)
                .stream()
                .map(conversaResponseAssembler::montar)
                .toList();
    }

    public List<ConversaResponse> listarPorCaso(UUID idCaso) {
        return conversaGateway.buscarPorCaso(idCaso)
                .stream()
                .map(conversaResponseAssembler::montar)
                .toList();
    }
}

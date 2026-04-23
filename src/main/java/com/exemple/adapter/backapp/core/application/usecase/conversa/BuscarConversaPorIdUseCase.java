package com.exemple.adapter.backapp.core.application.usecase.conversa;

import com.exemple.adapter.backapp.core.adapter.gateway.ConversaGateway;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.ConversaResponse;
import com.exemple.adapter.backapp.core.domain.Conversa;

import java.util.NoSuchElementException;
import java.util.UUID;

public class BuscarConversaPorIdUseCase {

    private final ConversaGateway conversaGateway;
    private final ConversaResponseAssembler conversaResponseAssembler;

    public BuscarConversaPorIdUseCase(ConversaGateway conversaGateway,
                                      ConversaResponseAssembler conversaResponseAssembler) {
        this.conversaGateway = conversaGateway;
        this.conversaResponseAssembler = conversaResponseAssembler;
    }

    public ConversaResponse executar(UUID idConversa) {
        Conversa conversa = conversaGateway.buscarPorId(idConversa)
                .orElseThrow(() -> new NoSuchElementException("Conversa nao encontrada"));

        return conversaResponseAssembler.montar(conversa);
    }
}

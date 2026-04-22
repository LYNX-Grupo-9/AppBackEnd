package com.exemple.adapter.backapp.core.application.usecase.conversa;

import com.exemple.adapter.backapp.core.adapter.gateway.ConversaGateway;
import com.exemple.adapter.backapp.core.application.dto.command.conversa.CriarConversaCommand;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.CriarConversaResponse;
import com.exemple.adapter.backapp.core.application.exception.DuplicidadeException;
import com.exemple.adapter.backapp.core.domain.Conversa;

public class CriarConversaUseCase {

    private final ConversaGateway conversaGateway;

    public CriarConversaUseCase(ConversaGateway conversaGateway) {
        this.conversaGateway = conversaGateway;
    }

    public CriarConversaResponse executar(CriarConversaCommand command) {
        if (conversaGateway.existePorParticipantesECaso(command.idCliente(), command.idAdvogado(), command.idCaso())) {
            throw new DuplicidadeException("Já existe uma conversa para este cliente, advogado e caso");
        }

        Conversa conversa = Conversa.criarNovo(
                command.idCliente(),
                command.idAdvogado(),
                command.idCaso()
        );

        Conversa conversaCriada = conversaGateway.criar(conversa);

        return new CriarConversaResponse(
                conversaCriada.getIdConversa(),
                conversaCriada.getIdCliente(),
                conversaCriada.getIdAdvogado(),
                conversaCriada.getIdCaso()
        );
    }
}

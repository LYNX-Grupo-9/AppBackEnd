package com.exemple.adapter.backapp.core.application.usecase.mensagem;

import com.exemple.adapter.backapp.core.adapter.gateway.ConversaGateway;
import com.exemple.adapter.backapp.core.adapter.gateway.MensagemGateway;
import com.exemple.adapter.backapp.core.application.dto.response.mensagem.MensagemResponse;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class ListarMensagensPorConversaUseCase {

    private final ConversaGateway conversaGateway;
    private final MensagemGateway mensagemGateway;

    public ListarMensagensPorConversaUseCase(ConversaGateway conversaGateway, MensagemGateway mensagemGateway) {
        this.conversaGateway = conversaGateway;
        this.mensagemGateway = mensagemGateway;
    }

    public List<MensagemResponse> executar(UUID idConversa) {
        if (conversaGateway.buscarPorId(idConversa).isEmpty()) {
            throw new NoSuchElementException("Conversa não encontrada");
        }

        return mensagemGateway.buscarPorConversa(idConversa)
                .stream()
                .map(mensagem -> new MensagemResponse(
                        mensagem.getIdMensagem(),
                        mensagem.getIdConversa(),
                        mensagem.getConteudo(),
                        mensagem.getRemetenteTipo(),
                        mensagem.getRemetenteId(),
                        mensagem.getEnviadoEm()
                ))
                .toList();
    }
}

package com.exemple.adapter.backapp.core.application.usecase.mensagem;

import com.exemple.adapter.backapp.core.adapter.gateway.MensagemGateway;
import com.exemple.adapter.backapp.core.application.dto.command.mensagem.EnviarMensagemCommand;
import com.exemple.adapter.backapp.core.application.dto.response.mensagem.EnviarMensagemResponse;
import com.exemple.adapter.backapp.core.domain.Mensagem;

public class EnviarMensagemUseCase {

    private final MensagemGateway mensagemGateway;

    public EnviarMensagemUseCase(MensagemGateway mensagemGateway) {
        this.mensagemGateway = mensagemGateway;
    }

    public EnviarMensagemResponse executar(EnviarMensagemCommand command) {

        Mensagem mensagem = Mensagem.enviar(
                command.idConversa(),
                command.conteudo(),
                command.remetenteTipo(),
                command.remetenteId()
        );

        Mensagem enviada = mensagemGateway.enviar(mensagem);

        return new EnviarMensagemResponse(
                enviada.getIdMensagem(),
                enviada.getConteudo(),
                enviada.getEnviadoEm()
        );
    }
}
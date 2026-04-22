package com.exemple.adapter.backapp.core.application.usecase.mensagem;

import com.exemple.adapter.backapp.core.adapter.gateway.ConversaGateway;
import com.exemple.adapter.backapp.core.adapter.gateway.MensagemGateway;
import com.exemple.adapter.backapp.core.application.dto.command.mensagem.EnviarMensagemCommand;
import com.exemple.adapter.backapp.core.application.dto.response.mensagem.EnviarMensagemResponse;
import com.exemple.adapter.backapp.core.domain.Conversa;
import com.exemple.adapter.backapp.core.domain.Mensagem;

import java.util.NoSuchElementException;

public class EnviarMensagemUseCase {

    private final ConversaGateway conversaGateway;
    private final MensagemGateway mensagemGateway;

    public EnviarMensagemUseCase(ConversaGateway conversaGateway, MensagemGateway mensagemGateway) {
        this.conversaGateway = conversaGateway;
        this.mensagemGateway = mensagemGateway;
    }

    public EnviarMensagemResponse executar(EnviarMensagemCommand command) {
        Conversa conversa = conversaGateway.buscarPorId(command.idConversa())
                .orElseThrow(() -> new NoSuchElementException("Conversa não encontrada"));

        validarRemetente(command, conversa);

        Mensagem mensagem = Mensagem.enviar(
                command.idConversa(),
                command.conteudo(),
                command.remetenteTipo(),
                command.remetenteId()
        );

        Mensagem enviada = mensagemGateway.enviar(mensagem);

        return new EnviarMensagemResponse(
                enviada.getIdMensagem(),
                enviada.getIdConversa(),
                enviada.getConteudo(),
                enviada.getRemetenteTipo(),
                enviada.getRemetenteId(),
                enviada.getEnviadoEm()
        );
    }

    private void validarRemetente(EnviarMensagemCommand command, Conversa conversa) {
        String remetenteTipo = command.remetenteTipo();

        if (remetenteTipo == null || remetenteTipo.isBlank()) {
            throw new IllegalArgumentException("Tipo do remetente é obrigatório");
        }

        if ("CLIENTE".equalsIgnoreCase(remetenteTipo)) {
            if (!conversa.getIdCliente().equals(command.remetenteId())) {
                throw new IllegalArgumentException("Cliente informado não participa desta conversa");
            }
            return;
        }

        if ("ADVOGADO".equalsIgnoreCase(remetenteTipo)) {
            if (!conversa.getIdAdvogado().equals(command.remetenteId())) {
                throw new IllegalArgumentException("Advogado informado não participa desta conversa");
            }
            return;
        }

        throw new IllegalArgumentException("Tipo do remetente deve ser CLIENTE ou ADVOGADO");
    }
}

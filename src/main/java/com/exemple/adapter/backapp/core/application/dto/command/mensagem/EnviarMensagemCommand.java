package com.exemple.adapter.backapp.core.application.dto.command.mensagem;

import java.util.UUID;

public record EnviarMensagemCommand(
        UUID idConversa,
        String conteudo,
        String remetenteTipo,
        UUID remetenteId
) {}
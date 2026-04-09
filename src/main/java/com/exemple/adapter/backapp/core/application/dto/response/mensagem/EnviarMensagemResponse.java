package com.exemple.adapter.backapp.core.application.dto.response.mensagem;

import java.time.LocalDateTime;
import java.util.UUID;

public record EnviarMensagemResponse(
        UUID idMensagem,
        String conteudo,
        LocalDateTime enviadoEm
) {}
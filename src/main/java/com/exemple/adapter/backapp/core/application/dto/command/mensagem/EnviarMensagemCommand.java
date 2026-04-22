package com.exemple.adapter.backapp.core.application.dto.command.mensagem;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record EnviarMensagemCommand(
        @Schema(description = "UUID da conversa que receberá a mensagem", example = "11111111-1111-1111-1111-111111111111")
        UUID idConversa,
        @Schema(description = "Texto da mensagem", example = "Olá, tudo bem?")
        String conteudo,
        @Schema(description = "Tipo do remetente. Valores aceitos: CLIENTE ou ADVOGADO", example = "CLIENTE")
        String remetenteTipo,
        @Schema(description = "UUID do remetente da mensagem", example = "22222222-2222-2222-2222-222222222222")
        UUID remetenteId
) {}

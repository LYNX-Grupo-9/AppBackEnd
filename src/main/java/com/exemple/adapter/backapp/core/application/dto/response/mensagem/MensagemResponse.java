package com.exemple.adapter.backapp.core.application.dto.response.mensagem;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record MensagemResponse(
        @Schema(description = "UUID da mensagem", example = "55555555-5555-5555-5555-555555555555")
        UUID idMensagem,
        @Schema(description = "UUID da conversa", example = "11111111-1111-1111-1111-111111111111")
        UUID idConversa,
        @Schema(description = "Conteúdo da mensagem", example = "Olá, tudo bem?")
        String conteudo,
        @Schema(description = "Tipo do remetente", example = "CLIENTE")
        String remetenteTipo,
        @Schema(description = "UUID do remetente", example = "22222222-2222-2222-2222-222222222222")
        UUID remetenteId,
        @Schema(description = "Data e hora de envio", example = "2026-04-20T20:45:00")
        LocalDateTime enviadoEm
) {}

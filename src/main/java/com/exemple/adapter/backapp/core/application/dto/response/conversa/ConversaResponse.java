package com.exemple.adapter.backapp.core.application.dto.response.conversa;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConversaResponse(
        @Schema(description = "UUID da conversa", example = "11111111-1111-1111-1111-111111111111")
        UUID idConversa,
        @Schema(description = "UUID do cliente da conversa", example = "22222222-2222-2222-2222-222222222222")
        UUID idCliente,
        @Schema(description = "UUID do advogado da conversa", example = "33333333-3333-3333-3333-333333333333")
        UUID idAdvogado,
        @Schema(description = "UUID do caso relacionado", example = "44444444-4444-4444-4444-444444444444")
        UUID idCaso,
        @Schema(description = "Data e hora de criação da conversa", example = "2026-04-20T20:40:00")
        LocalDateTime criadoEm
) {}

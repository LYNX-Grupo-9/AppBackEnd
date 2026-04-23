package com.exemple.adapter.backapp.core.application.dto.response.conversa;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.UUID;

public record ConversaResponse(
        @Schema(description = "UUID da conversa", example = "11111111-1111-1111-1111-111111111111")
        UUID idConversa,
        @Schema(description = "Objeto com os dados do cliente da conversa")
        ClienteResumoResponse cliente,
        @Schema(description = "Objeto com os dados do advogado da conversa")
        AdvogadoResumoResponse advogado,
        @Schema(description = "UUID do caso relacionado", example = "44444444-4444-4444-4444-444444444444")
        UUID idCaso,
        @Schema(description = "Data e hora de criacao da conversa", example = "2026-04-20T20:40:00")
        LocalDateTime criadoEm
) {}

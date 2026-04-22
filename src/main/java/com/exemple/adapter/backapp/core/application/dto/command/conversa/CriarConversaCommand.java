package com.exemple.adapter.backapp.core.application.dto.command.conversa;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record CriarConversaCommand(
        @Schema(description = "UUID do cliente dono da conversa", example = "22222222-2222-2222-2222-222222222222")
        UUID idCliente,
        @Schema(description = "UUID do advogado participante da conversa", example = "33333333-3333-3333-3333-333333333333")
        UUID idAdvogado,
        @Schema(description = "UUID do caso relacionado à conversa", example = "44444444-4444-4444-4444-444444444444")
        UUID idCaso
) {}

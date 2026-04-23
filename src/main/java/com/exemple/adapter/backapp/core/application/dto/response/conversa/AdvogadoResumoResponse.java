package com.exemple.adapter.backapp.core.application.dto.response.conversa;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record AdvogadoResumoResponse(
        @Schema(description = "UUID do advogado", example = "33333333-3333-3333-3333-333333333333")
        UUID idAdvogado,
        @Schema(description = "Nome do advogado", example = "Joao Pereira")
        String nome,
        @Schema(description = "Registro OAB do advogado", example = "OAB/SP 123456")
        String registroOab,
        @Schema(description = "CPF do advogado", example = "98765432100")
        String cpf,
        @Schema(description = "Email do advogado", example = "joao@adv.com")
        String email
) {}

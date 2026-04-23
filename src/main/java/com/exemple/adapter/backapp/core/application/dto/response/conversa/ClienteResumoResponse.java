package com.exemple.adapter.backapp.core.application.dto.response.conversa;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.UUID;

public record ClienteResumoResponse(
        @Schema(description = "UUID do cliente", example = "22222222-2222-2222-2222-222222222222")
        UUID idClienteApp,
        @Schema(description = "Nome do cliente", example = "Maria Silva")
        String nome,
        @Schema(description = "Email do cliente", example = "maria@email.com")
        String email,
        @Schema(description = "CPF do cliente", example = "12345678901")
        String cpf,
        @Schema(description = "UUID do advogado fixo do cliente, quando houver", example = "33333333-3333-3333-3333-333333333333")
        UUID advogadoFixoId
) {}

package com.exemple.adapter.backapp.core.application.dto.response.advogado;

import java.util.UUID;

public record AdvogadoPerfilResponse(
        UUID idAdvogado,
        String nome,
        String registroOab,
        String cpf,
        String email
) {}

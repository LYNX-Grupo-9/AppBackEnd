package com.exemple.adapter.backapp.core.application.dto.response.conversa;

import java.util.UUID;

public record CriarConversaResponse(
        UUID idCliente,
        UUID idAdvogado,
        UUID idCaso
) {}

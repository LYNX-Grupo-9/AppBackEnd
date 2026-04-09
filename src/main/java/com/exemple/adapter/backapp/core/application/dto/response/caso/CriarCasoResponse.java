package com.exemple.adapter.backapp.core.application.dto.response.caso;

import java.util.UUID;

public record CriarCasoResponse(
        UUID id,
        String titulo,
        String status
) {}
package com.exemple.adapter.backapp.core.application.dto.response.dashboard;

import java.util.UUID;

public record EngajamentoPorCasoResponse(
        UUID processoId,
        String titulo,
        String area,
        long interessados
) {}

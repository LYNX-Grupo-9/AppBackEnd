package com.exemple.adapter.backapp.core.application.dto.response.advogadoInteressado;

import com.exemple.adapter.backapp.core.application.dto.response.conversa.AdvogadoResumoResponse;

import java.util.UUID;

public record AdvogadoInteressadoResponse(
        UUID casoId,
        AdvogadoResumoResponse advogado,
        Boolean definitivo
) {}

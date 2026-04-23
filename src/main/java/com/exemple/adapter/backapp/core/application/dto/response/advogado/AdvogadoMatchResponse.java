package com.exemple.adapter.backapp.core.application.dto.response.advogado;

import com.exemple.adapter.backapp.core.application.dto.response.conversa.AdvogadoResumoResponse;

public record AdvogadoMatchResponse(
        AdvogadoResumoResponse advogado,
        Boolean definitivo
) {}

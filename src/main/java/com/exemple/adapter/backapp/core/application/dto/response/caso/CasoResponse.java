package com.exemple.adapter.backapp.core.application.dto.response.caso;

import java.time.LocalDate;
import java.util.UUID;

public record CasoResponse(
        UUID idCaso,
        String areaDireito,
        String titulo,
        String descricao,
        String status,
        LocalDate dataCriacao,
        String analiseIa
) {}

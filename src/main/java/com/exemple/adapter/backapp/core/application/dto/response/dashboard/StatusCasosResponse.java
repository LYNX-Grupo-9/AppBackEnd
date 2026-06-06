package com.exemple.adapter.backapp.core.application.dto.response.dashboard;

public record StatusCasosResponse(
        long total,
        long emAndamento,
        long aberto,
        long encerrado
) {}

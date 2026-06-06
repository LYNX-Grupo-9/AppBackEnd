package com.exemple.adapter.backapp.core.application.dto.response.dashboard;

import java.util.List;

public record DashboardClienteResponse(
        StatusCasosResponse statusCasos,
        List<CasosPorAreaResponse> casosPorArea,
        List<HistoricoCasosResponse> historicoCasos,
        List<EngajamentoPorCasoResponse> engajamentoPorCaso
) {}

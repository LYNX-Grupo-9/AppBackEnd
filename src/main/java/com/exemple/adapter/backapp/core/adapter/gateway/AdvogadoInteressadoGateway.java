package com.exemple.adapter.backapp.core.adapter.gateway;

import com.exemple.adapter.backapp.core.domain.AdvogadoInteressado;

import java.util.List;
import java.util.UUID;

public interface AdvogadoInteressadoGateway {
    AdvogadoInteressado criar(AdvogadoInteressado interessado);
    List<AdvogadoInteressado> buscarPorCaso(UUID casoId);
}
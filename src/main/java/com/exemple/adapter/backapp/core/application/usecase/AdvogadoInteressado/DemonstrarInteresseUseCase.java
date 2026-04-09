package com.exemple.adapter.backapp.core.application.usecase.AdvogadoInteressado;

import com.exemple.adapter.backapp.core.adapter.gateway.AdvogadoInteressadoGateway;
import com.exemple.adapter.backapp.core.domain.AdvogadoInteressado;

import java.util.UUID;

public class DemonstrarInteresseUseCase {

    private final AdvogadoInteressadoGateway gateway;

    public DemonstrarInteresseUseCase(AdvogadoInteressadoGateway gateway) {
        this.gateway = gateway;
    }

    public void executar(UUID advogadoId, UUID casoId) {
        AdvogadoInteressado interessado =
                AdvogadoInteressado.criarNovo(advogadoId, casoId);
        gateway.criar(interessado);
    }
}

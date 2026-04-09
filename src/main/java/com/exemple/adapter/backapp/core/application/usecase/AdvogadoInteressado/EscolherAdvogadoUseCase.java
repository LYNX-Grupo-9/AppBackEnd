package com.exemple.adapter.backapp.core.application.usecase.AdvogadoInteressado;

import com.exemple.adapter.backapp.core.adapter.gateway.AdvogadoInteressadoGateway;

import java.util.UUID;

public class EscolherAdvogadoUseCase {

    private final AdvogadoInteressadoGateway gateway;

    public EscolherAdvogadoUseCase(AdvogadoInteressadoGateway gateway) {
        this.gateway = gateway;
    }

    public void executar(UUID casoId, UUID advogadoEscolhidoId) {

        var interessados = gateway.buscarPorCaso(casoId);

        interessados.forEach(i -> {
            if (i.getAdvogadoId().equals(advogadoEscolhidoId)) {
                i.tornarDefinitivo();
            }
        });

        interessados.forEach(gateway::criar);
    }
}

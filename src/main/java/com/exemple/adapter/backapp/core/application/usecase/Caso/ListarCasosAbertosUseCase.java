package com.exemple.adapter.backapp.core.application.usecase.Caso;

import com.exemple.adapter.backapp.core.adapter.gateway.CasoGateway;
import com.exemple.adapter.backapp.core.application.dto.response.caso.CasoResponse;

import java.util.List;

public class ListarCasosAbertosUseCase {

    private static final String STATUS_ABERTO = "ABERTO";

    private final CasoGateway casoGateway;
    private final CasoResponseAssembler casoResponseAssembler;

    public ListarCasosAbertosUseCase(CasoGateway casoGateway,
                                     CasoResponseAssembler casoResponseAssembler) {
        this.casoGateway = casoGateway;
        this.casoResponseAssembler = casoResponseAssembler;
    }

    public List<CasoResponse> executar() {
        return casoGateway.buscarPorStatus(STATUS_ABERTO)
                .stream()
                .map(casoResponseAssembler::montar)
                .toList();
    }
}

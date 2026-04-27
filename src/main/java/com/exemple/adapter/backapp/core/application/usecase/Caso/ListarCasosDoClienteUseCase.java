package com.exemple.adapter.backapp.core.application.usecase.Caso;

import com.exemple.adapter.backapp.core.adapter.gateway.CasoGateway;
import com.exemple.adapter.backapp.core.application.dto.response.caso.CasoResponse;

import java.util.List;
import java.util.UUID;

public class ListarCasosDoClienteUseCase {

    private final CasoGateway casoGateway;
    private final CasoResponseAssembler casoResponseAssembler;

    public ListarCasosDoClienteUseCase(CasoGateway casoGateway,
                                       CasoResponseAssembler casoResponseAssembler) {
        this.casoGateway = casoGateway;
        this.casoResponseAssembler = casoResponseAssembler;
    }

    public List<CasoResponse> executar(UUID idCliente) {
        return casoGateway.buscarPorCliente(idCliente)
                .stream()
                .map(casoResponseAssembler::montar)
                .toList();
    }
}

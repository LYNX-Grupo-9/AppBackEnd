package com.exemple.adapter.backapp.core.application.usecase.Caso;

import com.exemple.adapter.backapp.core.adapter.gateway.CasoGateway;
import com.exemple.adapter.backapp.core.application.dto.response.caso.CasoResponse;
import com.exemple.adapter.backapp.core.domain.Caso;

import java.util.NoSuchElementException;
import java.util.UUID;

public class BuscarCasoPorIdDoClienteUseCase {

    private final CasoGateway casoGateway;
    private final CasoResponseAssembler casoResponseAssembler;

    public BuscarCasoPorIdDoClienteUseCase(CasoGateway casoGateway,
                                           CasoResponseAssembler casoResponseAssembler) {
        this.casoGateway = casoGateway;
        this.casoResponseAssembler = casoResponseAssembler;
    }

    public CasoResponse executar(UUID idCaso, UUID idClienteLogado) {
        Caso caso = casoGateway.buscarPorId(idCaso)
                .orElseThrow(() -> new NoSuchElementException("Caso nao encontrado"));

        if (!caso.getIdCliente().equals(idClienteLogado)) {
            throw new NoSuchElementException("Caso nao encontrado");
        }

        return casoResponseAssembler.montar(caso);
    }
}

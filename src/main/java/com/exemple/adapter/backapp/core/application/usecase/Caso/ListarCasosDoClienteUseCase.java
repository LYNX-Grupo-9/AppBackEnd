package com.exemple.adapter.backapp.core.application.usecase.Caso;

import com.exemple.adapter.backapp.core.adapter.gateway.CasoGateway;
import com.exemple.adapter.backapp.core.application.dto.response.caso.CasoResponse;

import java.util.List;
import java.util.UUID;

public class ListarCasosDoClienteUseCase {

    private final CasoGateway casoGateway;

    public ListarCasosDoClienteUseCase(CasoGateway casoGateway) {
        this.casoGateway = casoGateway;
    }

    public List<CasoResponse> executar(UUID idCliente) {
        return casoGateway.buscarPorCliente(idCliente)
                .stream()
                .map(caso -> new CasoResponse(
                        caso.getIdCaso(),
                        caso.getAreaDireito(),
                        caso.getTitulo(),
                        caso.getDescricao(),
                        caso.getStatus(),
                        caso.getDataCriacao(),
                        caso.getAnaliseIa()
                ))
                .toList();
    }
}

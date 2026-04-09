package com.exemple.adapter.backapp.core.application.usecase.Caso;

import com.exemple.adapter.backapp.core.adapter.gateway.CasoGateway;
import com.exemple.adapter.backapp.core.application.dto.command.caso.CriarCasoCommand;
import com.exemple.adapter.backapp.core.application.dto.response.caso.CriarCasoResponse;
import com.exemple.adapter.backapp.core.domain.Caso;

public class CriarCasoUseCase {

    private final CasoGateway casoGateway;

    public CriarCasoUseCase(CasoGateway casoGateway) {
        this.casoGateway = casoGateway;
    }

    public CriarCasoResponse executar(CriarCasoCommand command) {

        Caso caso = Caso.criarNovo(
                command.areaDireito(),
                command.titulo(),
                command.descricao(),
                command.analiseIa(),
                command.idCliente()
        );

        Caso casoCriado = casoGateway.criar(caso);

        return new CriarCasoResponse(
                casoCriado.getIdCaso(),
                casoCriado.getTitulo(),
                casoCriado.getStatus()
        );
    }
}

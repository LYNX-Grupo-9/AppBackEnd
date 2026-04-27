package com.exemple.adapter.backapp.core.application.usecase.Caso;

import com.exemple.adapter.backapp.core.application.dto.response.caso.CasoResponse;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.ClienteResumoResponse;
import com.exemple.adapter.backapp.core.domain.Caso;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ClienteAppEntity;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.ClienteAppRepository;

public class CasoResponseAssembler {

    private final ClienteAppRepository clienteAppRepository;

    public CasoResponseAssembler(ClienteAppRepository clienteAppRepository) {
        this.clienteAppRepository = clienteAppRepository;
    }

    public CasoResponse montar(Caso caso) {
        return new CasoResponse(
                caso.getIdCaso(),
                caso.getAreaDireito(),
                caso.getTitulo(),
                caso.getDescricao(),
                caso.getStatus(),
                caso.getDataCriacao(),
                caso.getAnaliseIa(),
                clienteAppRepository.findById(caso.getIdCliente())
                        .map(this::montarCliente)
                        .orElse(null)
        );
    }

    private ClienteResumoResponse montarCliente(ClienteAppEntity cliente) {
        return new ClienteResumoResponse(
                cliente.getIdClienteApp(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getAdvogadoFixoId()
        );
    }
}

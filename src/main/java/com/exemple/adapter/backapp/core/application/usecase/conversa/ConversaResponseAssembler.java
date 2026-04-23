package com.exemple.adapter.backapp.core.application.usecase.conversa;

import com.exemple.adapter.backapp.core.application.dto.response.conversa.AdvogadoResumoResponse;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.ClienteResumoResponse;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.ConversaResponse;
import com.exemple.adapter.backapp.core.domain.Conversa;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ClienteAppEntity;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoRepository;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.ClienteAppRepository;

public class ConversaResponseAssembler {

    private final ClienteAppRepository clienteAppRepository;
    private final AdvogadoRepository advogadoRepository;

    public ConversaResponseAssembler(ClienteAppRepository clienteAppRepository,
                                     AdvogadoRepository advogadoRepository) {
        this.clienteAppRepository = clienteAppRepository;
        this.advogadoRepository = advogadoRepository;
    }

    public ConversaResponse montar(Conversa conversa) {
        return new ConversaResponse(
                conversa.getIdConversa(),
                buscarCliente(conversa),
                buscarAdvogado(conversa),
                conversa.getIdCaso(),
                conversa.getCriadoEm()
        );
    }

    private ClienteResumoResponse buscarCliente(Conversa conversa) {
        return clienteAppRepository.findById(conversa.getIdCliente())
                .map(this::montarCliente)
                .orElse(null);
    }

    private AdvogadoResumoResponse buscarAdvogado(Conversa conversa) {
        return advogadoRepository.findById(conversa.getIdAdvogado())
                .map(this::montarAdvogado)
                .orElse(null);
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

    private AdvogadoResumoResponse montarAdvogado(AdvogadoEntity advogado) {
        return new AdvogadoResumoResponse(
                advogado.getIdAdvogado(),
                advogado.getNome(),
                advogado.getRegistroOab(),
                advogado.getCpf(),
                advogado.getEmail()
        );
    }
}

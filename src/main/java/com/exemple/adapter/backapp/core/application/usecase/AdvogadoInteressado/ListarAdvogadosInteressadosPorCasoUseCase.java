package com.exemple.adapter.backapp.core.application.usecase.AdvogadoInteressado;

import com.exemple.adapter.backapp.core.adapter.gateway.AdvogadoInteressadoGateway;
import com.exemple.adapter.backapp.core.adapter.gateway.CasoGateway;
import com.exemple.adapter.backapp.core.application.dto.response.advogado.AdvogadoMatchResponse;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.AdvogadoResumoResponse;
import com.exemple.adapter.backapp.core.domain.Caso;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoRepository;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

public class ListarAdvogadosInteressadosPorCasoUseCase {

    private final CasoGateway casoGateway;
    private final AdvogadoInteressadoGateway advogadoInteressadoGateway;
    private final AdvogadoRepository advogadoRepository;

    public ListarAdvogadosInteressadosPorCasoUseCase(CasoGateway casoGateway,
                                                     AdvogadoInteressadoGateway advogadoInteressadoGateway,
                                                     AdvogadoRepository advogadoRepository) {
        this.casoGateway = casoGateway;
        this.advogadoInteressadoGateway = advogadoInteressadoGateway;
        this.advogadoRepository = advogadoRepository;
    }

    public List<AdvogadoMatchResponse> executar(UUID idCaso, UUID idClienteLogado) {
        Caso caso = casoGateway.buscarPorId(idCaso)
                .orElseThrow(() -> new NoSuchElementException("Caso nao encontrado"));

        if (!caso.getIdCliente().equals(idClienteLogado)) {
            throw new NoSuchElementException("Caso nao encontrado");
        }

        return advogadoInteressadoGateway.buscarPorCaso(idCaso)
                .stream()
                .map(interessado -> advogadoRepository.findById(interessado.getAdvogadoId())
                        .map(advogado -> new AdvogadoMatchResponse(
                                new AdvogadoResumoResponse(
                                        advogado.getIdAdvogado(),
                                        advogado.getNome(),
                                        advogado.getRegistroOab(),
                                        advogado.getCpf(),
                                        advogado.getEmail()
                                ),
                                interessado.getDefinitivo()
                        ))
                        .orElse(null))
                .filter(java.util.Objects::nonNull)
                .toList();
    }
}

package com.exemple.adapter.backapp.core.application.usecase.AdvogadoInteressado;

import com.exemple.adapter.backapp.core.adapter.gateway.AdvogadoInteressadoGateway;
import com.exemple.adapter.backapp.core.application.dto.response.advogadoInteressado.AdvogadoInteressadoResponse;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.AdvogadoResumoResponse;
import com.exemple.adapter.backapp.core.domain.AdvogadoInteressado;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

public class DemonstrarInteresseUseCase {

    private final AdvogadoInteressadoGateway gateway;
    private final AdvogadoRepository advogadoRepository;

    public DemonstrarInteresseUseCase(AdvogadoInteressadoGateway gateway,
                                      AdvogadoRepository advogadoRepository) {
        this.gateway = gateway;
        this.advogadoRepository = advogadoRepository;
    }

    public AdvogadoInteressadoResponse executar(UUID advogadoId, UUID casoId) {
        AdvogadoInteressado interessado =
                AdvogadoInteressado.criarNovo(advogadoId, casoId);
        AdvogadoInteressado criado = gateway.criar(interessado);

        AdvogadoResumoResponse advogado = advogadoRepository.findById(criado.getAdvogadoId())
                .map(entidade -> new AdvogadoResumoResponse(
                        entidade.getIdAdvogado(),
                        entidade.getNome(),
                        entidade.getRegistroOab(),
                        entidade.getCpf(),
                        entidade.getEmail()
                ))
                .orElseThrow(() -> new NoSuchElementException("Advogado nao encontrado"));

        return new AdvogadoInteressadoResponse(
                criado.getCasoId(),
                advogado,
                criado.getDefinitivo()
        );
    }
}

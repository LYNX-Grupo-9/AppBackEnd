package com.exemple.adapter.backapp.core.application.usecase.Advogado;

import com.exemple.adapter.backapp.core.application.dto.response.advogado.AdvogadoPerfilResponse;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoRepository;

import java.util.NoSuchElementException;
import java.util.UUID;

public class BuscarPerfilAdvogadoUseCase {

    private final AdvogadoRepository advogadoRepository;

    public BuscarPerfilAdvogadoUseCase(AdvogadoRepository advogadoRepository) {
        this.advogadoRepository = advogadoRepository;
    }

    public AdvogadoPerfilResponse executar(UUID idAdvogado) {
        return advogadoRepository.findById(idAdvogado)
                .map(advogado -> new AdvogadoPerfilResponse(
                        advogado.getIdAdvogado(),
                        advogado.getNome(),
                        advogado.getRegistroOab(),
                        advogado.getCpf(),
                        advogado.getEmail()
                ))
                .orElseThrow(() -> new NoSuchElementException("Advogado nao encontrado"));
    }
}

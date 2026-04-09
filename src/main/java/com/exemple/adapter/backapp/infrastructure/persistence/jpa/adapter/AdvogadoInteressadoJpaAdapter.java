package com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter;

import com.exemple.adapter.backapp.core.adapter.gateway.AdvogadoInteressadoGateway;
import com.exemple.adapter.backapp.core.domain.AdvogadoInteressado;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.mapper.AdvogadoInteressadoMapper;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoInteressadoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class AdvogadoInteressadoJpaAdapter implements AdvogadoInteressadoGateway {

    private final AdvogadoInteressadoRepository repository;

    public AdvogadoInteressadoJpaAdapter(AdvogadoInteressadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public AdvogadoInteressado criar(AdvogadoInteressado domain) {
        var entity = AdvogadoInteressadoMapper.toEntity(domain);
        var salvo = repository.save(entity);
        return AdvogadoInteressadoMapper.toDomain(salvo);
    }

    @Override
    public List<AdvogadoInteressado> buscarPorCaso(UUID casoId) {
        return repository.findByCasoId(casoId)
                .stream()
                .map(AdvogadoInteressadoMapper::toDomain)
                .toList();
    }
}

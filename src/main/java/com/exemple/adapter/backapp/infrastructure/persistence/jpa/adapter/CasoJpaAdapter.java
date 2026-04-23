package com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter;

import com.exemple.adapter.backapp.core.adapter.gateway.CasoGateway;
import com.exemple.adapter.backapp.core.domain.Caso;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.CasoEntity;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.mapper.CasoMapper;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.CasoJpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class CasoJpaAdapter implements CasoGateway {

    private final CasoJpaRepository repository;

    public CasoJpaAdapter(CasoJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public Caso criar(Caso caso) {

        CasoEntity entity = CasoMapper.toEntity(caso);

        CasoEntity salvo = repository.save(entity);

        return CasoMapper.toDomain(salvo);
    }

    @Override
    public List<Caso> buscarPorCliente(UUID idCliente) {
        return repository.findByIdClienteOrderByDataCriacaoDesc(idCliente)
                .stream()
                .map(CasoMapper::toDomain)
                .toList();
    }

    @Override
    public Optional<Caso> buscarPorId(UUID idCaso) {
        return repository.findById(idCaso)
                .map(CasoMapper::toDomain);
    }
}

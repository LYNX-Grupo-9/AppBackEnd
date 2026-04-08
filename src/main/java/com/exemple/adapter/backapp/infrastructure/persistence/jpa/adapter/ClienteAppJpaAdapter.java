package com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter;

import com.exemple.adapter.backapp.core.adapter.gateway.ClienteAppGateway;
import com.exemple.adapter.backapp.core.domain.ClienteApp;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ClienteAppEntity;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.mapper.ClienteAppMapper;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.ClienteAppRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public class ClienteAppJpaAdapter implements ClienteAppGateway {

    private final ClienteAppRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public ClienteAppJpaAdapter(ClienteAppRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public ClienteApp criar(ClienteApp domain) {
        ClienteAppEntity entity = ClienteAppMapper.toEntity(domain);
        entityManager.persist(entity);
        entityManager.flush();
        return ClienteAppMapper.toDomain(entity);
    }

    @Override
    public boolean existePorEmail(String email) {
        return repository.existsByEmail(email);
    }

    @Override
    public boolean existePorCpf(String cpf) {
        return repository.existsByCpf(cpf);
    }

    @Override
    public Optional<ClienteApp> buscarPorEmail(String email) {
        return repository.findByEmail(email)
                .map(ClienteAppMapper::toDomain);
    }
}
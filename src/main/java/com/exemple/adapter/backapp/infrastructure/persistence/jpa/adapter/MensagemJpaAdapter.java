package com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter;

import com.exemple.adapter.backapp.core.adapter.gateway.MensagemGateway;
import com.exemple.adapter.backapp.core.domain.Mensagem;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.MensagemEntity;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.mapper.MensagemMapper;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.MensagemJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class MensagemJpaAdapter implements MensagemGateway {

    private final MensagemJpaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public MensagemJpaAdapter(MensagemJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Mensagem enviar(Mensagem domain) {
        MensagemEntity entity = MensagemMapper.toEntity(domain);
        entityManager.persist(entity);
        entityManager.flush();
        return MensagemMapper.toDomain(entity);
    }

    @Override
    public List<Mensagem> buscarPorConversa(UUID idConversa) {
        return repository.findByIdConversa(idConversa)
                .stream()
                .map(MensagemMapper::toDomain)
                .toList();
    }
}
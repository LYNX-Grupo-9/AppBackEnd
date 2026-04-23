package com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter;

import com.exemple.adapter.backapp.core.adapter.gateway.ConversaGateway;
import com.exemple.adapter.backapp.core.domain.Conversa;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ConversaEntity;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.mapper.ConversaMapper;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.ConversaJpaRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class ConversaJpaAdapter implements ConversaGateway {

    private final ConversaJpaRepository repository;

    @PersistenceContext
    private EntityManager entityManager;

    public ConversaJpaAdapter(ConversaJpaRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public Conversa criar(Conversa domain) {
        ConversaEntity entity = ConversaMapper.toEntity(domain);
        entityManager.persist(entity);
        entityManager.flush();
        return ConversaMapper.toDomain(entity);
    }

    @Override
    public Optional<Conversa> buscarPorId(UUID idConversa) {
        return repository.findById(idConversa)
                .map(ConversaMapper::toDomain);
    }

    @Override
    public List<Conversa> buscarPorCliente(UUID idCliente) {
        return repository.findByIdClienteOrderByCriadoEmDesc(idCliente)
                .stream()
                .map(ConversaMapper::toDomain)
                .toList();
    }

    @Override
    public List<Conversa> buscarPorAdvogado(UUID idAdvogado) {
        return repository.findByIdAdvogadoOrderByCriadoEmDesc(idAdvogado)
                .stream()
                .map(ConversaMapper::toDomain)
                .toList();
    }

    @Override
    public List<Conversa> buscarPorCaso(UUID idCaso) {
        return repository.findByIdCasoOrderByCriadoEmDesc(idCaso)
                .stream()
                .map(ConversaMapper::toDomain)
                .toList();
    }

    @Override
    public boolean existePorParticipantesECaso(UUID idCliente, UUID idAdvogado, UUID idCaso) {
        return repository.existsByIdClienteAndIdAdvogadoAndIdCaso(idCliente, idAdvogado, idCaso);
    }
}

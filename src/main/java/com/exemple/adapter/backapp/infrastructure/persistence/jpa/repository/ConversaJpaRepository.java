package com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository;

import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ConversaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConversaJpaRepository extends JpaRepository<ConversaEntity, UUID> {
    List<ConversaEntity> findByIdCliente(UUID idCliente);
    List<ConversaEntity> findByIdAdvogado(UUID idAdvogado);

}
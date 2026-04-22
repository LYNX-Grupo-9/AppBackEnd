package com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository;

import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.MensagemEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface MensagemJpaRepository extends JpaRepository<MensagemEntity, UUID> {
    List<MensagemEntity> findByIdConversaOrderByEnviadoEmAsc(UUID idConversa);
}

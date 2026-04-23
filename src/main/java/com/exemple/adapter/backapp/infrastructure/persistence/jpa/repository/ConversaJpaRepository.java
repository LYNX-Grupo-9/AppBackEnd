package com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository;

import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ConversaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ConversaJpaRepository extends JpaRepository<ConversaEntity, UUID> {
    List<ConversaEntity> findByIdClienteOrderByCriadoEmDesc(UUID idCliente);
    List<ConversaEntity> findByIdAdvogadoOrderByCriadoEmDesc(UUID idAdvogado);
    List<ConversaEntity> findByIdCasoOrderByCriadoEmDesc(UUID idCaso);
    boolean existsByIdClienteAndIdAdvogadoAndIdCaso(UUID idCliente, UUID idAdvogado, UUID idCaso);

}

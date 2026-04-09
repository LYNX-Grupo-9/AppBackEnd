package com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository;

import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.CasoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface CasoJpaRepository extends JpaRepository<CasoEntity, Integer> {
    List<CasoEntity> findByIdCliente(UUID idCliente);
}

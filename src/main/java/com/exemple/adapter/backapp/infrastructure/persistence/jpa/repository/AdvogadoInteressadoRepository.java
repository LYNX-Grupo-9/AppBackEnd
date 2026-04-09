package com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository;

import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.AdvogadoInteressadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AdvogadoInteressadoRepository extends JpaRepository<AdvogadoInteressadoEntity, UUID> {
    List<AdvogadoInteressadoEntity> findByCasoId(UUID casoId);
}
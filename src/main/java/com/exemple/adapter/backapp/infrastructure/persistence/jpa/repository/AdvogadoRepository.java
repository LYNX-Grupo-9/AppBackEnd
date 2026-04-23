package com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository;

import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdvogadoRepository extends JpaRepository<AdvogadoEntity, UUID> {
}

package com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository;

import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ClienteAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ClienteAppRepository extends JpaRepository<ClienteAppEntity, UUID> {
    boolean existsByEmail(String email);
    boolean existsByCpf(String cpf);
    Optional<ClienteAppEntity> findByEmail(String email);
}
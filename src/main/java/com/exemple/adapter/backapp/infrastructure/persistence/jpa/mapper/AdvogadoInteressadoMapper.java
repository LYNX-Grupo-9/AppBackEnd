package com.exemple.adapter.backapp.infrastructure.persistence.jpa.mapper;
import com.exemple.adapter.backapp.core.domain.AdvogadoInteressado;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.AdvogadoInteressadoEntity;

import java.util.UUID;

public class AdvogadoInteressadoMapper {
    public static AdvogadoInteressadoEntity toEntity(AdvogadoInteressado domain) {
        var entity = new AdvogadoInteressadoEntity();

        entity.setId(UUID.randomUUID());
        entity.setAdvogadoId(domain.getAdvogadoId());
        entity.setCasoId(domain.getCasoId());
        entity.setDefinitivo(domain.getDefinitivo());

        return entity;
    }

    public static AdvogadoInteressado toDomain(AdvogadoInteressadoEntity entity) {
        return new AdvogadoInteressado(
                entity.getAdvogadoId(),
                entity.getCasoId(),
                entity.getDefinitivo()
        );
    }
}
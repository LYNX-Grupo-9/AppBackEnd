package com.exemple.adapter.backapp.infrastructure.persistence.jpa.mapper;

import com.exemple.adapter.backapp.core.domain.Caso;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.CasoEntity;

public class CasoMapper {

    public static CasoEntity toEntity(Caso domain) {
        var entity = new CasoEntity();

        entity.setIdCaso(domain.getIdCaso());
        entity.setAreaDireito(domain.getAreaDireito());
        entity.setTitulo(domain.getTitulo());
        entity.setDescricao(domain.getDescricao());
        entity.setStatus(domain.getStatus());
        entity.setDataCriacao(domain.getDataCriacao());
        entity.setAnaliseIa(domain.getAnaliseIa());
        entity.setIdCliente(domain.getIdCliente());

        return entity;
    }

    public static Caso toDomain(CasoEntity entity) {
        return new Caso(
                entity.getIdCaso(),
                entity.getAreaDireito(),
                entity.getTitulo(),
                entity.getDescricao(),
                entity.getStatus(),
                entity.getDataCriacao(),
                entity.getAnaliseIa(),
                entity.getIdCliente()
        );
    }
}

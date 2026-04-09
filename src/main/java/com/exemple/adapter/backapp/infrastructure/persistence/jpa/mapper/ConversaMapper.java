package com.exemple.adapter.backapp.infrastructure.persistence.jpa.mapper;


import com.exemple.adapter.backapp.core.domain.Conversa;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ConversaEntity;

public class ConversaMapper {

    public static ConversaEntity toEntity(Conversa domain) {
        var entity = new ConversaEntity();

        entity.setIdConversa(domain.getIdConversa());
        entity.setIdCliente(domain.getIdCliente());
        entity.setIdAdvogado(domain.getIdAdvogado());
        entity.setIdCaso(domain.getIdCaso());
        entity.setCriadoEm(domain.getCriadoEm());

        return entity;
    }

    public static Conversa toDomain(ConversaEntity entity) {
        return new Conversa(
                entity.getIdConversa(),
                entity.getIdCliente(),
                entity.getIdAdvogado(),
                entity.getIdCaso(),
                entity.getCriadoEm()
        );
    }
}
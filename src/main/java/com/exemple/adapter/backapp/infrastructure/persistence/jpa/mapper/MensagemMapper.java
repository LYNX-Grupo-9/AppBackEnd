package com.exemple.adapter.backapp.infrastructure.persistence.jpa.mapper;

import com.exemple.adapter.backapp.core.domain.Mensagem;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.MensagemEntity;

public class MensagemMapper {

    public static MensagemEntity toEntity(Mensagem domain) {
        var entity = new MensagemEntity();

        entity.setIdMensagem(domain.getIdMensagem());
        entity.setIdConversa(domain.getIdConversa());
        entity.setConteudo(domain.getConteudo());
        entity.setRemetenteTipo(domain.getRemetenteTipo());
        entity.setRemetenteId(domain.getRemetenteId());
        entity.setEnviadoEm(domain.getEnviadoEm());

        return entity;
    }

    public static Mensagem toDomain(MensagemEntity entity) {
        return new Mensagem(
                entity.getIdMensagem(),
                entity.getIdConversa(),
                entity.getConteudo(),
                entity.getRemetenteTipo(),
                entity.getRemetenteId(),
                entity.getEnviadoEm()
        );
    }
}
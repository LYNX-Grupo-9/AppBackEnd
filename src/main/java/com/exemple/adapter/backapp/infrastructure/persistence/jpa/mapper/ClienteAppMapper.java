package com.exemple.adapter.backapp.infrastructure.persistence.jpa.mapper;

import com.exemple.adapter.backapp.core.domain.ClienteApp;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ClienteAppEntity;

public class ClienteAppMapper {

    public static ClienteAppEntity toEntity(ClienteApp domain) {
        var entity = new ClienteAppEntity();

        entity.setIdClienteApp(domain.getIdClienteApp());
        entity.setNome(domain.getNome());
        entity.setEmail(domain.getEmail());
        entity.setCpf(domain.getCpf());
        entity.setSenha(domain.getSenha().getValor());
        entity.setAdvogadoFixoId(domain.getAdvogadoFixo());

        return entity;
    }

    public static ClienteApp toDomain(ClienteAppEntity entity) {
        return ClienteApp.criarNovo(
                entity.getNome(),
                entity.getEmail(),
                entity.getCpf(),
                entity.getSenha()
        );
    }
}
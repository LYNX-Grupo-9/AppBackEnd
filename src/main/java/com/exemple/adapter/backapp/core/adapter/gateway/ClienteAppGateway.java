package com.exemple.adapter.backapp.core.adapter.gateway;

import com.exemple.adapter.backapp.core.domain.ClienteApp;

import java.util.Optional;

public interface ClienteAppGateway {
    ClienteApp criar(ClienteApp cliente);
    boolean existePorEmail(String email);
    boolean existePorCpf(String cpf);
    Optional<ClienteApp> buscarPorEmail(String email);
}

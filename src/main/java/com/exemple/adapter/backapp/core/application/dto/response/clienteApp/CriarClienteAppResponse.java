package com.exemple.adapter.backapp.core.application.dto.response.clienteApp;

import java.util.UUID;

public record CriarClienteAppResponse(
        UUID id,
        String nome,
        String email,
        String cpf
) {}
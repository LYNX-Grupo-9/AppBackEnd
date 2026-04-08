package com.exemple.adapter.backapp.core.application.dto.response.clienteApp;

import java.util.UUID;

public record ClienteToken(
        UUID id,
        String nome,
        String email,
        String token
) {}
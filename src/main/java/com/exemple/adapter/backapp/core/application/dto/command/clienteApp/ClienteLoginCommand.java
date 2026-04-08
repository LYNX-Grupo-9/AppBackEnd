package com.exemple.adapter.backapp.core.application.dto.command.clienteApp;

public record ClienteLoginCommand(
        String email,
        String senha
) {}
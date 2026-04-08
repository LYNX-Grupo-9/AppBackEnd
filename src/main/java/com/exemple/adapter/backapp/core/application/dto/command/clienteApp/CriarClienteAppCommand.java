package com.exemple.adapter.backapp.core.application.dto.command.clienteApp;

public record CriarClienteAppCommand(
        String nome,
        String email,
        String cpf,
        String senha
) {}
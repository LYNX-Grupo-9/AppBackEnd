package com.exemple.adapter.backapp.core.application.dto.command.caso;

import java.util.UUID;

public record CriarCasoCommand(
        String areaDireito,
        String titulo,
        String descricao,
        String analiseIa,
        UUID idCliente
) {}

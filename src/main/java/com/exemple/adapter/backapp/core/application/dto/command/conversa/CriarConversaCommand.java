package com.exemple.adapter.backapp.core.application.dto.command.conversa;

import java.util.UUID;

public record CriarConversaCommand(
        UUID idCliente,
        UUID idAdvogado,
        UUID idCaso
) {}

package com.exemple.adapter.backapp.infrastructure.web.websocket;

import java.util.UUID;

public record ChatWebSocketRequest(
        String type,
        UUID idConversa,
        String conteudo,
        String remetenteTipo
) {}

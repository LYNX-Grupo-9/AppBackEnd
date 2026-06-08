package com.exemple.adapter.backapp.infrastructure.web.websocket;

public record ChatWebSocketEvent(
        String type,
        Object data
) {}

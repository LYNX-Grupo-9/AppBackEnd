package com.exemple.adapter.backapp.infrastructure.web.websocket;

import com.exemple.adapter.backapp.core.application.dto.command.mensagem.EnviarMensagemCommand;
import com.exemple.adapter.backapp.core.application.dto.response.mensagem.EnviarMensagemResponse;
import com.exemple.adapter.backapp.core.application.usecase.mensagem.EnviarMensagemUseCase;
import com.exemple.adapter.backapp.infrastructure.config.GerenciadorTokenJwt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ChatWebSocketHandler extends TextWebSocketHandler {

    private static final String TYPE_SUBSCRIBE = "SUBSCRIBE";
    private static final String TYPE_SEND = "SEND";

    private final EnviarMensagemUseCase enviarMensagemUseCase;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;
    private final ObjectMapper objectMapper;
    private final Map<UUID, Set<WebSocketSession>> sessoesPorConversa = new ConcurrentHashMap<>();

    public ChatWebSocketHandler(EnviarMensagemUseCase enviarMensagemUseCase,
                                GerenciadorTokenJwt gerenciadorTokenJwt,
                                ObjectMapper objectMapper) {
        this.enviarMensagemUseCase = enviarMensagemUseCase;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
        this.objectMapper = objectMapper;
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        String token = extrairToken(session);
        if (token != null && !token.isBlank()) {
            session.getAttributes().put("userId", gerenciadorTokenJwt.getUserIdFromToken(token));
        }

        enviarParaSessao(session, new ChatWebSocketEvent("CONNECTED", null));
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        ChatWebSocketRequest request = objectMapper.readValue(message.getPayload(), ChatWebSocketRequest.class);

        if (request.type() == null || request.type().isBlank()) {
            enviarErro(session, "Tipo do evento e obrigatorio");
            return;
        }

        if (TYPE_SUBSCRIBE.equalsIgnoreCase(request.type())) {
            assinarConversa(session, request);
            return;
        }

        if (TYPE_SEND.equalsIgnoreCase(request.type())) {
            enviarMensagem(session, request);
            return;
        }

        enviarErro(session, "Tipo do evento invalido");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
        sessoesPorConversa.values()
                .forEach(sessoes -> sessoes.remove(session));
    }

    private void assinarConversa(WebSocketSession session, ChatWebSocketRequest request) throws IOException {
        if (request.idConversa() == null) {
            enviarErro(session, "idConversa e obrigatorio para assinar uma conversa");
            return;
        }

        sessoesPorConversa
                .computeIfAbsent(request.idConversa(), ignored -> ConcurrentHashMap.newKeySet())
                .add(session);

        enviarParaSessao(session, new ChatWebSocketEvent("SUBSCRIBED", Map.of("idConversa", request.idConversa())));
    }

    private void enviarMensagem(WebSocketSession session, ChatWebSocketRequest request) throws IOException {
        UUID remetenteId = (UUID) session.getAttributes().get("userId");

        if (remetenteId == null) {
            enviarErro(session, "Token JWT e obrigatorio para enviar mensagem pelo WebSocket");
            return;
        }

        EnviarMensagemResponse response = enviarMensagemUseCase.executar(new EnviarMensagemCommand(
                request.idConversa(),
                request.conteudo(),
                request.remetenteTipo(),
                remetenteId
        ));

        broadcast(response.idConversa(), new ChatWebSocketEvent("MESSAGE", response));
    }

    private void broadcast(UUID idConversa, ChatWebSocketEvent event) throws IOException {
        Set<WebSocketSession> sessoes = sessoesPorConversa.getOrDefault(idConversa, Collections.emptySet());
        String payload = objectMapper.writeValueAsString(event);

        for (WebSocketSession session : sessoes) {
            if (session.isOpen()) {
                session.sendMessage(new TextMessage(payload));
            }
        }
    }

    public void publicarMensagem(EnviarMensagemResponse response) {
        try {
            broadcast(response.idConversa(), new ChatWebSocketEvent("MESSAGE", response));
        } catch (IOException ignored) {
        }
    }

    private void enviarParaSessao(WebSocketSession session, ChatWebSocketEvent event) throws IOException {
        if (session.isOpen()) {
            session.sendMessage(new TextMessage(objectMapper.writeValueAsString(event)));
        }
    }

    private void enviarErro(WebSocketSession session, String mensagem) throws IOException {
        enviarParaSessao(session, new ChatWebSocketEvent("ERROR", Map.of("message", mensagem)));
    }

    private String extrairToken(WebSocketSession session) {
        if (session.getUri() == null || session.getUri().getQuery() == null) {
            return null;
        }

        String[] params = session.getUri().getQuery().split("&");
        for (String param : params) {
            String[] partes = param.split("=", 2);
            if (partes.length == 2 && "token".equals(partes[0])) {
                return URLDecoder.decode(partes[1], StandardCharsets.UTF_8);
            }
        }

        return null;
    }
}

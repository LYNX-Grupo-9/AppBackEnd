package com.exemple.adapter.backapp.infrastructure.web.controller;

import com.exemple.adapter.backapp.core.application.dto.command.mensagem.EnviarMensagemCommand;
import com.exemple.adapter.backapp.core.application.dto.response.mensagem.EnviarMensagemResponse;
import com.exemple.adapter.backapp.core.application.usecase.mensagem.EnviarMensagemUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mensagens")
public class MensagemController {

    private final EnviarMensagemUseCase useCase;

    public MensagemController(EnviarMensagemUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<EnviarMensagemResponse> enviar(
            @RequestBody EnviarMensagemCommand command) {

        EnviarMensagemResponse response = useCase.executar(command);

        return ResponseEntity.status(201).body(response);
    }
}
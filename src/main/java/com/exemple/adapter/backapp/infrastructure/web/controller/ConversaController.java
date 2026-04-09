package com.exemple.adapter.backapp.infrastructure.web.controller;

import com.exemple.adapter.backapp.core.application.dto.command.conversa.CriarConversaCommand;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.CriarConversaResponse;
import com.exemple.adapter.backapp.core.application.usecase.conversa.CriarConversaUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/conversas")
public class ConversaController {

    private final CriarConversaUseCase useCase;

    public ConversaController(CriarConversaUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<CriarConversaResponse> criar(
            @RequestBody CriarConversaCommand command) {
        CriarConversaResponse response = useCase.executar(command);
        return ResponseEntity.status(201).body(response);
    }
}

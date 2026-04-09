package com.exemple.adapter.backapp.infrastructure.web.controller;

import com.exemple.adapter.backapp.core.application.dto.command.caso.CriarCasoCommand;
import com.exemple.adapter.backapp.core.application.dto.response.caso.CriarCasoResponse;
import com.exemple.adapter.backapp.core.application.usecase.Caso.CriarCasoUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/casos")
public class CasoController {

    private final CriarCasoUseCase useCase;

    public CasoController(CriarCasoUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    public ResponseEntity<CriarCasoResponse> criar(
            @RequestBody CriarCasoCommand command) {

        CriarCasoResponse response = useCase.executar(command);

        return ResponseEntity.status(201).body(response);
    }
}

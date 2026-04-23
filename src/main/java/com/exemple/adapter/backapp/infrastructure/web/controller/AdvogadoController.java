package com.exemple.adapter.backapp.infrastructure.web.controller;

import com.exemple.adapter.backapp.core.application.dto.response.advogado.AdvogadoPerfilResponse;
import com.exemple.adapter.backapp.core.application.usecase.Advogado.BuscarPerfilAdvogadoUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/advogados")
@SecurityRequirement(name = "Bearer")
public class AdvogadoController {

    private final BuscarPerfilAdvogadoUseCase buscarPerfilAdvogadoUseCase;

    public AdvogadoController(BuscarPerfilAdvogadoUseCase buscarPerfilAdvogadoUseCase) {
        this.buscarPerfilAdvogadoUseCase = buscarPerfilAdvogadoUseCase;
    }

    @GetMapping("/{id}/perfil")
    public ResponseEntity<AdvogadoPerfilResponse> buscarPerfil(@PathVariable UUID id) {
        return ResponseEntity.ok(buscarPerfilAdvogadoUseCase.executar(id));
    }
}

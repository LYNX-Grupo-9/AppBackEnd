package com.exemple.adapter.backapp.infrastructure.web.controller;

import com.exemple.adapter.backapp.core.application.dto.response.dashboard.DashboardClienteResponse;
import com.exemple.adapter.backapp.core.application.usecase.dashboard.BuscarDashboardClienteUseCase;
import com.exemple.adapter.backapp.infrastructure.config.GerenciadorTokenJwt;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/clientes/dashboard")
@SecurityRequirement(name = "Bearer")
public class DashboardClienteController {

    private final BuscarDashboardClienteUseCase buscarDashboardClienteUseCase;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    public DashboardClienteController(BuscarDashboardClienteUseCase buscarDashboardClienteUseCase,
                                      GerenciadorTokenJwt gerenciadorTokenJwt) {
        this.buscarDashboardClienteUseCase = buscarDashboardClienteUseCase;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
    }

    @GetMapping
    public ResponseEntity<DashboardClienteResponse> buscarDashboard(
            @RequestHeader("Authorization") String authorizationHeader) {

        UUID idCliente = extrairIdClienteLogado(authorizationHeader);
        return ResponseEntity.ok(buscarDashboardClienteUseCase.executar(idCliente));
    }

    private UUID extrairIdClienteLogado(String authorizationHeader) {
        return gerenciadorTokenJwt.getUserIdFromToken(extrairTokenBearer(authorizationHeader));
    }

    private String extrairTokenBearer(String authorizationHeader) {
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new IllegalArgumentException("Header Authorization invalido");
        }

        return authorizationHeader.substring(7);
    }
}

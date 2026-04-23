package com.exemple.adapter.backapp.infrastructure.web.controller;

import com.exemple.adapter.backapp.core.application.dto.command.caso.CriarCasoCommand;
import com.exemple.adapter.backapp.core.application.dto.response.caso.CasoResponse;
import com.exemple.adapter.backapp.core.application.dto.response.caso.CriarCasoResponse;
import com.exemple.adapter.backapp.core.application.usecase.Caso.BuscarCasoPorIdDoClienteUseCase;
import com.exemple.adapter.backapp.core.application.usecase.Caso.CriarCasoUseCase;
import com.exemple.adapter.backapp.core.application.usecase.Caso.ListarCasosDoClienteUseCase;
import com.exemple.adapter.backapp.infrastructure.config.GerenciadorTokenJwt;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/casos")
@SecurityRequirement(name = "Bearer")
public class CasoController {

    private final CriarCasoUseCase criarCasoUseCase;
    private final ListarCasosDoClienteUseCase listarCasosDoClienteUseCase;
    private final BuscarCasoPorIdDoClienteUseCase buscarCasoPorIdDoClienteUseCase;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    public CasoController(CriarCasoUseCase criarCasoUseCase,
                          ListarCasosDoClienteUseCase listarCasosDoClienteUseCase,
                          BuscarCasoPorIdDoClienteUseCase buscarCasoPorIdDoClienteUseCase,
                          GerenciadorTokenJwt gerenciadorTokenJwt) {
        this.criarCasoUseCase = criarCasoUseCase;
        this.listarCasosDoClienteUseCase = listarCasosDoClienteUseCase;
        this.buscarCasoPorIdDoClienteUseCase = buscarCasoPorIdDoClienteUseCase;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
    }

    @GetMapping
    public ResponseEntity<List<CasoResponse>> listarDoUsuarioLogado(
            @RequestHeader("Authorization") String authorizationHeader) {

        UUID idCliente = extrairIdClienteLogado(authorizationHeader);
        return ResponseEntity.ok(listarCasosDoClienteUseCase.executar(idCliente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasoResponse> buscarPorId(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String authorizationHeader) {

        UUID idCliente = extrairIdClienteLogado(authorizationHeader);
        return ResponseEntity.ok(buscarCasoPorIdDoClienteUseCase.executar(id, idCliente));
    }

    @PostMapping
    public ResponseEntity<CriarCasoResponse> criar(
            @RequestBody CriarCasoCommand command) {

        CriarCasoResponse response = criarCasoUseCase.executar(command);

        return ResponseEntity.status(201).body(response);
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

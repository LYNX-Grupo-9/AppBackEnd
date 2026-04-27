package com.exemple.adapter.backapp.infrastructure.web.controller;

import com.exemple.adapter.backapp.core.application.dto.command.caso.CriarCasoCommand;
import com.exemple.adapter.backapp.core.application.dto.response.advogado.AdvogadoMatchResponse;
import com.exemple.adapter.backapp.core.application.dto.response.advogadoInteressado.AdvogadoInteressadoResponse;
import com.exemple.adapter.backapp.core.application.dto.response.caso.CasoResponse;
import com.exemple.adapter.backapp.core.application.dto.response.caso.CriarCasoResponse;
import com.exemple.adapter.backapp.core.application.usecase.AdvogadoInteressado.DemonstrarInteresseUseCase;
import com.exemple.adapter.backapp.core.application.usecase.AdvogadoInteressado.ListarAdvogadosInteressadosPorCasoUseCase;
import com.exemple.adapter.backapp.core.application.usecase.Caso.BuscarCasoPorIdDoClienteUseCase;
import com.exemple.adapter.backapp.core.application.usecase.Caso.CriarCasoUseCase;
import com.exemple.adapter.backapp.core.application.usecase.Caso.ListarCasosAbertosUseCase;
import com.exemple.adapter.backapp.core.application.usecase.Caso.ListarCasosDoClienteUseCase;
import com.exemple.adapter.backapp.infrastructure.config.GerenciadorTokenJwt;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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
    private final ListarCasosAbertosUseCase listarCasosAbertosUseCase;
    private final BuscarCasoPorIdDoClienteUseCase buscarCasoPorIdDoClienteUseCase;
    private final DemonstrarInteresseUseCase demonstrarInteresseUseCase;
    private final ListarAdvogadosInteressadosPorCasoUseCase listarAdvogadosInteressadosPorCasoUseCase;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    public CasoController(CriarCasoUseCase criarCasoUseCase,
                          ListarCasosDoClienteUseCase listarCasosDoClienteUseCase,
                          ListarCasosAbertosUseCase listarCasosAbertosUseCase,
                          BuscarCasoPorIdDoClienteUseCase buscarCasoPorIdDoClienteUseCase,
                          DemonstrarInteresseUseCase demonstrarInteresseUseCase,
                          ListarAdvogadosInteressadosPorCasoUseCase listarAdvogadosInteressadosPorCasoUseCase,
                          GerenciadorTokenJwt gerenciadorTokenJwt) {
        this.criarCasoUseCase = criarCasoUseCase;
        this.listarCasosDoClienteUseCase = listarCasosDoClienteUseCase;
        this.listarCasosAbertosUseCase = listarCasosAbertosUseCase;
        this.buscarCasoPorIdDoClienteUseCase = buscarCasoPorIdDoClienteUseCase;
        this.demonstrarInteresseUseCase = demonstrarInteresseUseCase;
        this.listarAdvogadosInteressadosPorCasoUseCase = listarAdvogadosInteressadosPorCasoUseCase;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
    }

    @GetMapping
    public ResponseEntity<List<CasoResponse>> listarDoUsuarioLogado(
            @RequestHeader("Authorization") String authorizationHeader) {

        UUID idCliente = extrairIdClienteLogado(authorizationHeader);
        return ResponseEntity.ok(listarCasosDoClienteUseCase.executar(idCliente));
    }

    @GetMapping("/abertos")
    @PreAuthorize("hasRole('ADVOGADO')")
    public ResponseEntity<List<CasoResponse>> listarCasosAbertos() {
        return ResponseEntity.ok(listarCasosAbertosUseCase.executar());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CasoResponse> buscarPorId(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String authorizationHeader) {

        UUID idCliente = extrairIdClienteLogado(authorizationHeader);
        return ResponseEntity.ok(buscarCasoPorIdDoClienteUseCase.executar(id, idCliente));
    }

    @GetMapping("/{id}/advogados-interessados")
    public ResponseEntity<List<AdvogadoMatchResponse>> listarAdvogadosInteressados(
            @PathVariable UUID id,
            @RequestHeader("Authorization") String authorizationHeader) {

        UUID idCliente = extrairIdClienteLogado(authorizationHeader);
        return ResponseEntity.ok(listarAdvogadosInteressadosPorCasoUseCase.executar(id, idCliente));
    }

    @PostMapping
    public ResponseEntity<CriarCasoResponse> criar(
            @RequestBody CriarCasoCommand command) {

        CriarCasoResponse response = criarCasoUseCase.executar(command);

        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/{id}/advogados-interessado/{idAdvogado}")
    public ResponseEntity<AdvogadoInteressadoResponse> demonstrarInteresse(
            @PathVariable UUID id,
            @PathVariable UUID idAdvogado) {

        AdvogadoInteressadoResponse response = demonstrarInteresseUseCase.executar(idAdvogado, id);
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

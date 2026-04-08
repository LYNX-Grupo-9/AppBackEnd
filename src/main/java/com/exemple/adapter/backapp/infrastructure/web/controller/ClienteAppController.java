package com.exemple.adapter.backapp.infrastructure.web.controller;


import com.exemple.adapter.backapp.core.application.dto.command.clienteApp.ClienteLoginCommand;
import com.exemple.adapter.backapp.core.application.dto.command.clienteApp.CriarClienteAppCommand;
import com.exemple.adapter.backapp.core.application.dto.response.clienteApp.ClienteToken;
import com.exemple.adapter.backapp.core.application.dto.response.clienteApp.CriarClienteAppResponse;
import com.exemple.adapter.backapp.core.application.usecase.ClienteApp.CriarClienteAppUseCase;
import com.exemple.adapter.backapp.infrastructure.config.GerenciadorTokenJwt;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ClienteAppEntity;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.ClienteAppRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clientes")
public class ClienteAppController {

    private final CriarClienteAppUseCase useCase;
    private final AuthenticationManager authenticationManager;
    private final ClienteAppRepository repository;
    private final GerenciadorTokenJwt gerenciadorTokenJwt;

    public ClienteAppController(CriarClienteAppUseCase useCase, AuthenticationManager authenticationManager, ClienteAppRepository repository, GerenciadorTokenJwt gerenciadorTokenJwt) {
        this.useCase = useCase;
        this.authenticationManager = authenticationManager;
        this.repository = repository;
        this.gerenciadorTokenJwt = gerenciadorTokenJwt;
    }

    @PostMapping("/cadastrar")
    public ResponseEntity<CriarClienteAppResponse> criar(
            @RequestBody CriarClienteAppCommand command) {

        CriarClienteAppResponse response = useCase.executar(command);
        return ResponseEntity.status(201).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<ClienteToken> login(@RequestBody ClienteLoginCommand command) {

        final UsernamePasswordAuthenticationToken credentials =
                new UsernamePasswordAuthenticationToken(command.email(), command.senha());

        final Authentication authentication = authenticationManager.authenticate(credentials);

        ClienteAppEntity cliente = repository.findByEmail(command.email())
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final String token = gerenciadorTokenJwt.gerarToken(authentication);

        ClienteToken response = new ClienteToken(
                cliente.getIdClienteApp(),
                cliente.getNome(),
                cliente.getEmail(),
                token
        );

        return ResponseEntity.ok(response);
    }
}
package com.exemple.adapter.backapp.core.application.usecase.ClienteApp;

import com.exemple.adapter.backapp.core.adapter.gateway.ClienteAppGateway;
import com.exemple.adapter.backapp.core.application.dto.command.clienteApp.CriarClienteAppCommand;
import com.exemple.adapter.backapp.core.application.dto.response.clienteApp.CriarClienteAppResponse;
import com.exemple.adapter.backapp.core.application.exception.DuplicidadeException;
import com.exemple.adapter.backapp.core.domain.ClienteApp;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CriarClienteAppUseCase {

    private final ClienteAppGateway clienteAppGateway;
    private final PasswordEncoder passwordEncoder;

    public CriarClienteAppUseCase(ClienteAppGateway clienteAppGateway,
                                  PasswordEncoder passwordEncoder) {
        this.clienteAppGateway = clienteAppGateway;
        this.passwordEncoder = passwordEncoder;
    }

    public CriarClienteAppResponse executar(CriarClienteAppCommand command) {

        String senhaCriptografada = passwordEncoder.encode(command.senha());

        if (clienteAppGateway.existePorEmail(command.email())) {
            throw new DuplicidadeException("Email já cadastrado");
        }

        if (clienteAppGateway.existePorCpf(command.cpf())) {
            throw new DuplicidadeException("CPF já cadastrado");
        }

        ClienteApp cliente = ClienteApp.criarNovo(
                command.nome(),
                command.email(),
                command.cpf(),
                senhaCriptografada
        );

        ClienteApp clienteCriado = clienteAppGateway.criar(cliente);

        return new CriarClienteAppResponse(
                clienteCriado.getIdClienteApp(),
                clienteCriado.getNome(),
                clienteCriado.getEmail(),
                clienteCriado.getCpf()
        );
    }
}
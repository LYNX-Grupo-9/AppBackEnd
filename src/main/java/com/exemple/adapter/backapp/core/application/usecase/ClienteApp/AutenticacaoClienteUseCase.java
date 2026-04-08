package com.exemple.adapter.backapp.core.application.usecase.cliente;

import com.exemple.adapter.backapp.core.application.dto.response.cliente.ClienteAppDetalhes;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ClienteAppEntity;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.ClienteAppRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AutenticacaoClienteUseCase implements UserDetailsService {

    private final ClienteAppRepository repository;

    public AutenticacaoClienteUseCase(ClienteAppRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {

        Optional<ClienteAppEntity> clienteOpt = repository.findByEmail(username);

        if (clienteOpt.isEmpty()) {
            throw new UsernameNotFoundException("Cliente não encontrado");
        }

        return new ClienteAppDetalhes(clienteOpt.get());
    }
}
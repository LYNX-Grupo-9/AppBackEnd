package com.exemple.adapter.backapp.core.application.usecase.advogado;

import com.exemple.adapter.backapp.core.application.dto.response.advogado.AdvogadoDetalhes;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AutenticacaoAdvogadoUseCase implements UserDetailsService {

    private final AdvogadoRepository repository;

    public AutenticacaoAdvogadoUseCase(AdvogadoRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<AdvogadoEntity> advogadoOpt = repository.findByEmail(username);

        if (advogadoOpt.isEmpty()) {
            throw new UsernameNotFoundException("Advogado nao encontrado");
        }

        return new AdvogadoDetalhes(advogadoOpt.get());
    }
}

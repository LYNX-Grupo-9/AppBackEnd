package com.exemple.adapter.backapp.infrastructure.di;

import com.exemple.adapter.backapp.core.application.usecase.AdvogadoInteressado.DemonstrarInteresseUseCase;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter.AdvogadoInteressadoJpaAdapter;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdvogadoInteressadoBeanConfig {

    @Bean
    public DemonstrarInteresseUseCase demonstrarInteresseUseCase(AdvogadoInteressadoJpaAdapter adapter,
                                                                 AdvogadoRepository advogadoRepository) {
        return new DemonstrarInteresseUseCase(adapter, advogadoRepository);
    }
}

package com.exemple.adapter.backapp.infrastructure.di;

import com.exemple.adapter.backapp.core.application.usecase.Caso.BuscarCasoPorIdDoClienteUseCase;
import com.exemple.adapter.backapp.core.application.usecase.Caso.CriarCasoUseCase;
import com.exemple.adapter.backapp.core.application.usecase.Caso.ListarCasosDoClienteUseCase;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter.CasoJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CasoBeanConfig {

    @Bean
    public CriarCasoUseCase criarCasoUseCase(CasoJpaAdapter adapter) {
        return new CriarCasoUseCase(adapter);
    }

    @Bean
    public ListarCasosDoClienteUseCase listarCasosDoClienteUseCase(CasoJpaAdapter adapter) {
        return new ListarCasosDoClienteUseCase(adapter);
    }

    @Bean
    public BuscarCasoPorIdDoClienteUseCase buscarCasoPorIdDoClienteUseCase(CasoJpaAdapter adapter) {
        return new BuscarCasoPorIdDoClienteUseCase(adapter);
    }
}

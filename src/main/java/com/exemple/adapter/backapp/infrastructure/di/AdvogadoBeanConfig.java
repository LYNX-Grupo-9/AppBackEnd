package com.exemple.adapter.backapp.infrastructure.di;

import com.exemple.adapter.backapp.core.application.usecase.Advogado.BuscarPerfilAdvogadoUseCase;
import com.exemple.adapter.backapp.core.application.usecase.AdvogadoInteressado.ListarAdvogadosInteressadosPorCasoUseCase;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter.AdvogadoInteressadoJpaAdapter;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter.CasoJpaAdapter;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AdvogadoBeanConfig {

    @Bean
    public BuscarPerfilAdvogadoUseCase buscarPerfilAdvogadoUseCase(AdvogadoRepository advogadoRepository) {
        return new BuscarPerfilAdvogadoUseCase(advogadoRepository);
    }

    @Bean
    public ListarAdvogadosInteressadosPorCasoUseCase listarAdvogadosInteressadosPorCasoUseCase(
            CasoJpaAdapter casoJpaAdapter,
            AdvogadoInteressadoJpaAdapter advogadoInteressadoJpaAdapter,
            AdvogadoRepository advogadoRepository) {
        return new ListarAdvogadosInteressadosPorCasoUseCase(
                casoJpaAdapter,
                advogadoInteressadoJpaAdapter,
                advogadoRepository
        );
    }
}

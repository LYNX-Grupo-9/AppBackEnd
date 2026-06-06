package com.exemple.adapter.backapp.infrastructure.di;

import com.exemple.adapter.backapp.core.application.usecase.dashboard.BuscarDashboardClienteUseCase;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoInteressadoRepository;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.CasoJpaRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DashboardBeanConfig {

    @Bean
    public BuscarDashboardClienteUseCase buscarDashboardClienteUseCase(CasoJpaRepository casoJpaRepository,
                                                                       AdvogadoInteressadoRepository advogadoInteressadoRepository) {
        return new BuscarDashboardClienteUseCase(casoJpaRepository, advogadoInteressadoRepository);
    }
}

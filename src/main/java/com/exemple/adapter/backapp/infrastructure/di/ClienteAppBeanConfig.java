package com.exemple.adapter.backapp.infrastructure.di;

import com.exemple.adapter.backapp.core.application.usecase.ClienteApp.CriarClienteAppUseCase;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter.ClienteAppJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class ClienteAppBeanConfig {

    @Bean
    public CriarClienteAppUseCase criarClienteAppUseCase(ClienteAppJpaAdapter adapter,
                                                         PasswordEncoder passwordEncoder) {
        return new CriarClienteAppUseCase(adapter, passwordEncoder);
    }
}
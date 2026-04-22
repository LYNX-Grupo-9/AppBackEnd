package com.exemple.adapter.backapp.infrastructure.di;

import com.exemple.adapter.backapp.core.application.usecase.conversa.BuscarConversaPorIdUseCase;
import com.exemple.adapter.backapp.core.application.usecase.conversa.CriarConversaUseCase;
import com.exemple.adapter.backapp.core.application.usecase.conversa.ListarConversasUseCase;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter.ConversaJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversaBeanConfig {

    @Bean
    public CriarConversaUseCase criarConversaUseCase(ConversaJpaAdapter adapter) {
        return new CriarConversaUseCase(adapter);
    }

    @Bean
    public BuscarConversaPorIdUseCase buscarConversaPorIdUseCase(ConversaJpaAdapter adapter) {
        return new BuscarConversaPorIdUseCase(adapter);
    }

    @Bean
    public ListarConversasUseCase listarConversasUseCase(ConversaJpaAdapter adapter) {
        return new ListarConversasUseCase(adapter);
    }
}

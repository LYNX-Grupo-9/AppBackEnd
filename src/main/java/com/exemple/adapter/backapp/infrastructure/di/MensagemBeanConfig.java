package com.exemple.adapter.backapp.infrastructure.di;

import com.exemple.adapter.backapp.core.application.usecase.mensagem.EnviarMensagemUseCase;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter.MensagemJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MensagemBeanConfig {
    @Bean
    public EnviarMensagemUseCase enviarMensagemUseCase(MensagemJpaAdapter adapter) {
        return new EnviarMensagemUseCase(adapter);
    }
}
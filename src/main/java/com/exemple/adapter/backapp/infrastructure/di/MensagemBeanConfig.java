package com.exemple.adapter.backapp.infrastructure.di;

import com.exemple.adapter.backapp.core.application.usecase.mensagem.EnviarMensagemUseCase;
import com.exemple.adapter.backapp.core.application.usecase.mensagem.ListarMensagensPorConversaUseCase;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter.ConversaJpaAdapter;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter.MensagemJpaAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MensagemBeanConfig {
    @Bean
    public EnviarMensagemUseCase enviarMensagemUseCase(ConversaJpaAdapter conversaAdapter, MensagemJpaAdapter mensagemAdapter) {
        return new EnviarMensagemUseCase(conversaAdapter, mensagemAdapter);
    }

    @Bean
    public ListarMensagensPorConversaUseCase listarMensagensPorConversaUseCase(ConversaJpaAdapter conversaAdapter, MensagemJpaAdapter mensagemAdapter) {
        return new ListarMensagensPorConversaUseCase(conversaAdapter, mensagemAdapter);
    }
}

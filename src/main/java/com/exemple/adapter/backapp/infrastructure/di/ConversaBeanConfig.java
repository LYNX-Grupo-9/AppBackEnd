package com.exemple.adapter.backapp.infrastructure.di;

import com.exemple.adapter.backapp.core.application.usecase.conversa.BuscarConversaPorIdUseCase;
import com.exemple.adapter.backapp.core.application.usecase.conversa.ConversaResponseAssembler;
import com.exemple.adapter.backapp.core.application.usecase.conversa.CriarConversaUseCase;
import com.exemple.adapter.backapp.core.application.usecase.conversa.ListarConversasUseCase;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter.ConversaJpaAdapter;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoRepository;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.ClienteAppRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConversaBeanConfig {

    @Bean
    public CriarConversaUseCase criarConversaUseCase(ConversaJpaAdapter adapter) {
        return new CriarConversaUseCase(adapter);
    }

    @Bean
    public ConversaResponseAssembler conversaResponseAssembler(ClienteAppRepository clienteAppRepository,
                                                               AdvogadoRepository advogadoRepository) {
        return new ConversaResponseAssembler(clienteAppRepository, advogadoRepository);
    }

    @Bean
    public BuscarConversaPorIdUseCase buscarConversaPorIdUseCase(ConversaJpaAdapter adapter,
                                                                 ConversaResponseAssembler conversaResponseAssembler) {
        return new BuscarConversaPorIdUseCase(adapter, conversaResponseAssembler);
    }

    @Bean
    public ListarConversasUseCase listarConversasUseCase(ConversaJpaAdapter adapter,
                                                         ConversaResponseAssembler conversaResponseAssembler) {
        return new ListarConversasUseCase(adapter, conversaResponseAssembler);
    }
}

package com.exemple.adapter.backapp.infrastructure.di;

import com.exemple.adapter.backapp.core.application.usecase.Caso.BuscarCasoPorIdDoClienteUseCase;
import com.exemple.adapter.backapp.core.application.usecase.Caso.CasoResponseAssembler;
import com.exemple.adapter.backapp.core.application.usecase.Caso.CriarCasoUseCase;
import com.exemple.adapter.backapp.core.application.usecase.Caso.ListarCasosAbertosUseCase;
import com.exemple.adapter.backapp.core.application.usecase.Caso.ListarCasosDoClienteUseCase;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.adapter.CasoJpaAdapter;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.ClienteAppRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CasoBeanConfig {

    @Bean
    public CriarCasoUseCase criarCasoUseCase(CasoJpaAdapter adapter) {
        return new CriarCasoUseCase(adapter);
    }

    @Bean
    public CasoResponseAssembler casoResponseAssembler(ClienteAppRepository clienteAppRepository) {
        return new CasoResponseAssembler(clienteAppRepository);
    }

    @Bean
    public ListarCasosDoClienteUseCase listarCasosDoClienteUseCase(CasoJpaAdapter adapter,
                                                                   CasoResponseAssembler casoResponseAssembler) {
        return new ListarCasosDoClienteUseCase(adapter, casoResponseAssembler);
    }

    @Bean
    public ListarCasosAbertosUseCase listarCasosAbertosUseCase(CasoJpaAdapter adapter,
                                                               CasoResponseAssembler casoResponseAssembler) {
        return new ListarCasosAbertosUseCase(adapter, casoResponseAssembler);
    }

    @Bean
    public BuscarCasoPorIdDoClienteUseCase buscarCasoPorIdDoClienteUseCase(CasoJpaAdapter adapter,
                                                                           CasoResponseAssembler casoResponseAssembler) {
        return new BuscarCasoPorIdDoClienteUseCase(adapter, casoResponseAssembler);
    }
}

package com.exemple.adapter.backapp.Server;

import org.springframework.stereotype.Component;

/**
 * Classe Server para testes
 */
@Component
public class TesteServer {

    public String processarDados(String entrada) {
        return "Dados processados: " + entrada;
    }

    public boolean validarDados(String dados) {
        return dados != null && !dados.isEmpty();
    }
}


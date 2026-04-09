package com.exemple.adapter.backapp.core.adapter.gateway;

import com.exemple.adapter.backapp.core.domain.Mensagem;

import java.util.List;
import java.util.UUID;

public interface MensagemGateway {
    Mensagem enviar(Mensagem mensagem);
    List<Mensagem> buscarPorConversa(UUID idConversa);
}
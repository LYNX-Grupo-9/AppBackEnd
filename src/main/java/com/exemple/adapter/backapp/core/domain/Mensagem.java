package com.exemple.adapter.backapp.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Mensagem {

    private UUID idMensagem;
    private UUID idConversa;
    private String conteudo;
    private String remetenteTipo;
    private UUID remetenteId;
    private LocalDateTime enviadoEm;


    public Mensagem(UUID idMensagem, UUID idConversa, String conteudo, String remetenteTipo, UUID remetenteId, LocalDateTime enviadoEm) {
        this.idMensagem = idMensagem;
        this.idConversa = idConversa;
        this.conteudo = conteudo;
        this.remetenteTipo = remetenteTipo;
        this.remetenteId = remetenteId;
        this.enviadoEm = enviadoEm;
    }

    public static Mensagem enviar(UUID idConversa,
                                  String conteudo,
                                  String remetenteTipo,
                                  UUID remetenteId) {

        if (conteudo == null || conteudo.isBlank()) {
            throw new IllegalArgumentException("Mensagem não pode ser vazia");
        }

        return new Mensagem(
                UUID.randomUUID(),
                idConversa,
                conteudo,
                remetenteTipo,
                remetenteId,
                LocalDateTime.now()
        );
    }
}

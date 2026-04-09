package com.exemple.adapter.backapp.core.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Conversa {

    private UUID idConversa;
    private UUID idCliente;
    private UUID idAdvogado;
    private UUID idCaso;

    private LocalDateTime criadoEm;

    public Conversa(UUID idConversa, UUID idCliente, UUID idAdvogado, UUID idCaso, LocalDateTime criadoEm) {
        this.idConversa = idConversa;
        this.idCliente = idCliente;
        this.idAdvogado = idAdvogado;
        this.idCaso = idCaso;
        this.criadoEm = criadoEm;
    }

    public static Conversa criarNovo(UUID idCliente, UUID idAdvogado, UUID idCaso) {

        return new Conversa(
                UUID.randomUUID(),
                idCliente,
                idAdvogado,
                idCaso,
                LocalDateTime.now()
        );
    }
}

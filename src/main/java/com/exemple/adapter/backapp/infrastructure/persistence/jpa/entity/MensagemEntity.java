package com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "mensagem")
public class MensagemEntity {

    @Id
    private UUID idMensagem;

    private UUID idConversa;
    private String conteudo;
    private String remetenteTipo;
    private UUID remetenteId;

    private LocalDateTime enviadoEm;

    public MensagemEntity() {}

    public UUID getIdMensagem() {
        return idMensagem;
    }

    public void setIdMensagem(UUID idMensagem) {
        this.idMensagem = idMensagem;
    }

    public UUID getIdConversa() {
        return idConversa;
    }

    public void setIdConversa(UUID idConversa) {
        this.idConversa = idConversa;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public String getRemetenteTipo() {
        return remetenteTipo;
    }

    public void setRemetenteTipo(String remetenteTipo) {
        this.remetenteTipo = remetenteTipo;
    }

    public UUID getRemetenteId() {
        return remetenteId;
    }

    public void setRemetenteId(UUID remetenteId) {
        this.remetenteId = remetenteId;
    }

    public LocalDateTime getEnviadoEm() {
        return enviadoEm;
    }

    public void setEnviadoEm(LocalDateTime enviadoEm) {
        this.enviadoEm = enviadoEm;
    }
}
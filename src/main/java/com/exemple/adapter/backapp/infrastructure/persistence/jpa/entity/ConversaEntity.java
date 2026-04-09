package com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "conversa")
public class ConversaEntity {

    @Id
    private UUID idConversa;

    private UUID idCliente;
    private UUID idAdvogado;
    private UUID idCaso;

    private LocalDateTime criadoEm;

    public ConversaEntity() {}

    public UUID getIdConversa() {
        return idConversa;
    }

    public void setIdConversa(UUID idConversa) {
        this.idConversa = idConversa;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public UUID getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(UUID idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public UUID getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(UUID idCaso) {
        this.idCaso = idCaso;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }
}

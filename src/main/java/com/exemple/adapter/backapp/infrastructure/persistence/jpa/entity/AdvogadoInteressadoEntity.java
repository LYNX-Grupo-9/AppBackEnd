package com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "advogado_interessado")
public class AdvogadoInteressadoEntity {

    @Id
    private UUID id;

    private UUID advogadoId;
    private UUID casoId;
    private Boolean definitivo;

    public AdvogadoInteressadoEntity() {}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getAdvogadoId() {
        return advogadoId;
    }

    public void setAdvogadoId(UUID advogadoId) {
        this.advogadoId = advogadoId;
    }

    public UUID getCasoId() {
        return casoId;
    }

    public void setCasoId(UUID casoId) {
        this.casoId = casoId;
    }

    public Boolean getDefinitivo() {
        return definitivo;
    }

    public void setDefinitivo(Boolean definitivo) {
        this.definitivo = definitivo;
    }
}
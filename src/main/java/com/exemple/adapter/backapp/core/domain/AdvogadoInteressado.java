package com.exemple.adapter.backapp.core.domain;

import java.util.UUID;

public class AdvogadoInteressado {

    private UUID advogadoId;
    private UUID casoId;
    private Boolean definitivo;

    public AdvogadoInteressado(UUID advogadoId,
                               UUID casoId,
                               Boolean definitivo) {

        this.advogadoId = advogadoId;
        this.casoId = casoId;
        this.definitivo = definitivo;
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

    public void tornarDefinitivo() {
        this.definitivo = true;
    }

    public static AdvogadoInteressado criarNovo(UUID advogadoId, UUID casoId) {
        return new AdvogadoInteressado(
                advogadoId,
                casoId,
                false
        );
    }
}

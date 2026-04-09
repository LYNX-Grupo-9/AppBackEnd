package com.exemple.adapter.backapp.core.domain;

import java.time.LocalDate;
import java.util.UUID;

public class Caso {

    private UUID idCaso;
    private String areaDireito;
    private String titulo;
    private String descricao;
    private String status;
    private LocalDate dataCriacao;
    private String analiseIa;

    private UUID idCliente;

    public Caso(UUID idCaso, String areaDireito, String titulo, String descricao, String status, LocalDate dataCriacao, String analiseIa, UUID idCliente) {
        this.idCaso = idCaso;
        this.areaDireito = areaDireito;
        this.titulo = titulo;
        this.descricao = descricao;
        this.status = status;
        this.dataCriacao = dataCriacao;
        this.analiseIa = analiseIa;
        this.idCliente = idCliente;
    }

    public UUID getIdCaso() {
        return idCaso;
    }

    public void setIdCaso(UUID idCaso) {
        this.idCaso = idCaso;
    }

    public String getAreaDireito() {
        return areaDireito;
    }

    public void setAreaDireito(String areaDireito) {
        this.areaDireito = areaDireito;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDate dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public String getAnaliseIa() {
        return analiseIa;
    }

    public void setAnaliseIa(String analiseIa) {
        this.analiseIa = analiseIa;
    }

    public UUID getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(UUID idCliente) {
        this.idCliente = idCliente;
    }

    public static Caso criarNovo(String areaDireito,
                                 String titulo,
                                 String descricao,
                                 String analiseIa,
                                 UUID idCliente) {

        return new Caso(
                UUID.randomUUID(),
                areaDireito,
                titulo,
                descricao,
                "ABERTO",
                LocalDate.now(),
                analiseIa,
                idCliente
        );
    }
}

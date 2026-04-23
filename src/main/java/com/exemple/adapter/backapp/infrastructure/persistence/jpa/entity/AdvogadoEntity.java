package com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "advogado")
public class AdvogadoEntity {

    @Id
    private UUID idAdvogado;

    private String nome;
    private String registroOab;
    private String cpf;
    private String email;
    private String senha;

    public AdvogadoEntity() {}

    public UUID getIdAdvogado() {
        return idAdvogado;
    }

    public void setIdAdvogado(UUID idAdvogado) {
        this.idAdvogado = idAdvogado;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRegistroOab() {
        return registroOab;
    }

    public void setRegistroOab(String registroOab) {
        this.registroOab = registroOab;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

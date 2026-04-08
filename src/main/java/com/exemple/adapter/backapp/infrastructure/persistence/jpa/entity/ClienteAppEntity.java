package com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name = "cliente_app")
public class ClienteAppEntity {

    @Id
    private UUID idClienteApp;

    private String nome;
    private String email;
    private String cpf;
    private String senha;

    private UUID advogadoFixoId;

    public ClienteAppEntity() {}

    public UUID getIdClienteApp() {
        return idClienteApp;
    }

    public void setIdClienteApp(UUID idClienteApp) {
        this.idClienteApp = idClienteApp;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public UUID getAdvogadoFixoId() {
        return advogadoFixoId;
    }

    public void setAdvogadoFixoId(UUID advogadoFixoId) {
        this.advogadoFixoId = advogadoFixoId;
    }
}
package com.exemple.adapter.backapp.core.domain;

import com.exemple.adapter.backapp.core.domain.ValueObject.Senha;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClienteApp {

    private UUID idClienteApp;
    private String nome;
    private String email;
    private String cpf;
    private Senha senha;

    private List<UUID> conversas;
    private List<UUID> casos;

    private UUID advogadoFixo;

    public ClienteApp(UUID idClienteApp,
                      String nome,
                      String email,
                      String cpf,
                      Senha senha,
                      List<UUID> conversas,
                      List<UUID> casos,
                      UUID advogadoFixoId) {

        this.idClienteApp = idClienteApp;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
        this.conversas = conversas != null ? conversas : new ArrayList<>();
        this.casos = casos != null ? casos : new ArrayList<>();
        this.advogadoFixo = advogadoFixoId;
    }

    private ClienteApp() {}

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

    public Senha getSenha() {
        return senha;
    }

    public void setSenha(Senha senha) {
        this.senha = senha;
    }

    public List<UUID> getConversas() {
        return conversas;
    }

    public void setConversas(List<UUID> conversas) {
        this.conversas = conversas;
    }

    public List<UUID> getCasos() {
        return casos;
    }

    public void setCasos(List<UUID> casos) {
        this.casos = casos;
    }

    public UUID getAdvogadoFixo() {
        return advogadoFixo;
    }

    public void setAdvogadoFixo(UUID advogadoFixo) {
        this.advogadoFixo = advogadoFixo;
    }

    public static ClienteApp criarNovo(String nome, String email, String cpf, String senha) {

        String uniqueKey = cpf + email;
        UUID id = UUID.nameUUIDFromBytes(uniqueKey.getBytes());

        return new ClienteApp(
                id,
                nome,
                email,
                cpf,
                Senha.criar(senha),
                new ArrayList<>(),
                new ArrayList<>(),
                null
        );
    }
}

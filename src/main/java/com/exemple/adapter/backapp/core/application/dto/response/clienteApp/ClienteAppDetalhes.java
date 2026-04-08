package com.exemple.adapter.backapp.core.application.dto.response.cliente;

import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.ClienteAppEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class ClienteAppDetalhes implements UserDetails {

    private final ClienteAppEntity cliente;

    public ClienteAppDetalhes(ClienteAppEntity cliente) {
        this.cliente = cliente;
    }

    public String getNome() {
        return cliente.getNome();
    }

    public String getEmail() {
        return cliente.getEmail();
    }

    public String getSenha() {
        return cliente.getSenha();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_CLIENTE"));
    }

    @Override
    public String getPassword() {
        return cliente.getSenha();
    }

    @Override
    public String getUsername() {
        return cliente.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public ClienteAppEntity getCliente() {
        return cliente;
    }
}
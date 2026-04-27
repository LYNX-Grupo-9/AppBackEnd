package com.exemple.adapter.backapp.core.application.dto.response.advogado;

import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.AdvogadoEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AdvogadoDetalhes implements UserDetails {

    private final AdvogadoEntity advogado;

    public AdvogadoDetalhes(AdvogadoEntity advogado) {
        this.advogado = advogado;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADVOGADO"));
    }

    @Override
    public String getPassword() {
        return advogado.getSenha();
    }

    @Override
    public String getUsername() {
        return advogado.getEmail();
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

    public AdvogadoEntity getAdvogado() {
        return advogado;
    }
}

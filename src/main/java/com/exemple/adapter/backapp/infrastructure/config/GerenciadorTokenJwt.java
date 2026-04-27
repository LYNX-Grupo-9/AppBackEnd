package com.exemple.adapter.backapp.infrastructure.config;

import com.exemple.adapter.backapp.core.application.dto.response.advogado.AdvogadoDetalhes;
import com.exemple.adapter.backapp.core.application.dto.response.cliente.ClienteAppDetalhes;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoRepository;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.ClienteAppRepository;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class GerenciadorTokenJwt {

    private final ClienteAppRepository clienteAppRepository;
    private final AdvogadoRepository advogadoRepository;

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.validity}")
    private Long jwtTokenValidity;

    public GerenciadorTokenJwt(ClienteAppRepository clienteAppRepository,
                               AdvogadoRepository advogadoRepository) {
        this.clienteAppRepository = clienteAppRepository;
        this.advogadoRepository = advogadoRepository;
    }

    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public UUID getUserIdFromToken(String token) {
        String userId = getAllClaimsFromToken(token).get("userId", String.class);
        if (userId != null && !userId.isBlank()) {
            return UUID.fromString(userId);
        }

        String username = getUsernameFromToken(token);

        return clienteAppRepository.findByEmail(username)
                .map(cliente -> cliente.getIdClienteApp())
                .orElseGet(() -> advogadoRepository.findByEmail(username)
                        .map(advogado -> advogado.getIdAdvogado())
                        .orElse(null));
    }

    public String gerarToken(final Authentication authentication) {

        final String authorities = authentication.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));

        final UUID userId = extrairUserId(authentication);

        return Jwts.builder()
                .setClaims(Map.of(
                        "userId", userId.toString(),
                        "authorities", authorities
                ))
                .setSubject(authentication.getName())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + jwtTokenValidity * 1_000))
                .signWith(parseSecret())
                .compact();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    public boolean validadeToken(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return username.equalsIgnoreCase(userDetails.getUsername()) && !isTokenExpired(token);
    }

    private Boolean isTokenExpired(String token) {
        Date experationDate = getExpirationDateFromToken(token);
        return experationDate.before(new Date(System.currentTimeMillis()));
    }

    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(parseSecret())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private SecretKey parseSecret() {
        return Keys.hmacShaKeyFor(this.secret.getBytes(StandardCharsets.UTF_8));
    }

    private UUID extrairUserId(Authentication authentication) {
        Object principal = authentication.getPrincipal();

        if (principal instanceof ClienteAppDetalhes clienteAppDetalhes) {
            return clienteAppDetalhes.getCliente().getIdClienteApp();
        }

        if (principal instanceof AdvogadoDetalhes advogadoDetalhes) {
            return advogadoDetalhes.getAdvogado().getIdAdvogado();
        }

        throw new IllegalStateException("Nao foi possivel extrair o id do usuario autenticado para gerar o token");
    }
}

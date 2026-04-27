package com.exemple.adapter.backapp.infrastructure.config;

import com.exemple.adapter.backapp.core.application.usecase.advogado.AutenticacaoAdvogadoUseCase;
import com.exemple.adapter.backapp.core.application.usecase.cliente.AutenticacaoClienteUseCase;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoRepository;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.ClienteAppRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.*;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguracao {

    private final AutenticacaoClienteUseCase autenticacaoClienteUseCase;
    private final AutenticacaoAdvogadoUseCase autenticacaoAdvogadoUseCase;
    private final ClienteAppRepository clienteAppRepository;
    private final AdvogadoRepository advogadoRepository;

    public SecurityConfiguracao(AutenticacaoClienteUseCase autenticacaoClienteUseCase,
                                AutenticacaoAdvogadoUseCase autenticacaoAdvogadoUseCase,
                                ClienteAppRepository clienteAppRepository,
                                AdvogadoRepository advogadoRepository) {
        this.autenticacaoClienteUseCase = autenticacaoClienteUseCase;
        this.autenticacaoAdvogadoUseCase = autenticacaoAdvogadoUseCase;
        this.clienteAppRepository = clienteAppRepository;
        this.advogadoRepository = advogadoRepository;
    }

    private static final String[] URLS_PERMITIDAS = {
            "/swagger-ui/**",
            "/swagger-ui.html",
            "/swagger-resources/**",
            "/api/public/**",
            "/api/advogados/login",
            "/api/advogados/cadastrar",
            "/api/clientes/login",
            "/api/clientes/cadastrar",
            "/h2-console/**",
            "/error/**",
            "/v3/api-docs/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer::disable)

                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(URLS_PERMITIDAS).permitAll()
                        .anyRequest().authenticated()
                )

                .exceptionHandling(ex -> ex
                        .authenticationEntryPoint(new AutenticacaoEntryPoint()) // 🔥 SEM ciclo
                )

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtAutenticacaoFilterBean(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.authenticationProvider(new AutenticacaoProvider(autenticacaoClienteUseCase, passwordEncoder()));
        return builder.build();
    }

    @Bean
    public AutenticacaoFilter jwtAutenticacaoFilterBean() {
        return new AutenticacaoFilter(autenticacaoClienteUseCase, autenticacaoAdvogadoUseCase, jwtAuthenticationUtilBean());
    }

    @Bean
    public GerenciadorTokenJwt jwtAuthenticationUtilBean() {
        return new GerenciadorTokenJwt(clienteAppRepository, advogadoRepository);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 🌐 CORS
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOriginPatterns(List.of("*"));
        config.setAllowedMethods(Arrays.asList(
                "GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"
        ));
        config.setAllowedHeaders(List.of("*"));
        config.setExposedHeaders(List.of(HttpHeaders.CONTENT_DISPOSITION));
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return source;
    }
}

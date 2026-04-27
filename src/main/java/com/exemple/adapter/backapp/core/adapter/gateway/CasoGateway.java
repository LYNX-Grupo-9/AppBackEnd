package com.exemple.adapter.backapp.core.adapter.gateway;

import com.exemple.adapter.backapp.core.domain.Caso;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CasoGateway {
    Caso criar(Caso caso);
    List<Caso> buscarPorCliente(UUID idCliente);
    List<Caso> buscarPorStatus(String status);
    Optional<Caso> buscarPorId(UUID idCaso);
}

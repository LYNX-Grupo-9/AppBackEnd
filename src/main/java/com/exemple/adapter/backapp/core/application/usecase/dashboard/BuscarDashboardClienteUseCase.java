package com.exemple.adapter.backapp.core.application.usecase.dashboard;

import com.exemple.adapter.backapp.core.application.dto.response.dashboard.CasosPorAreaResponse;
import com.exemple.adapter.backapp.core.application.dto.response.dashboard.DashboardClienteResponse;
import com.exemple.adapter.backapp.core.application.dto.response.dashboard.EngajamentoPorCasoResponse;
import com.exemple.adapter.backapp.core.application.dto.response.dashboard.HistoricoCasosResponse;
import com.exemple.adapter.backapp.core.application.dto.response.dashboard.StatusCasosResponse;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.entity.CasoEntity;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.AdvogadoInteressadoRepository;
import com.exemple.adapter.backapp.infrastructure.persistence.jpa.repository.CasoJpaRepository;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Collectors;

public class BuscarDashboardClienteUseCase {

    private static final int QUANTIDADE_MESES_HISTORICO = 6;
    private static final Locale LOCALE_BR = Locale.forLanguageTag("pt-BR");
    private static final DateTimeFormatter MES_FORMATTER = DateTimeFormatter.ofPattern("MMM", LOCALE_BR);

    private final CasoJpaRepository casoJpaRepository;
    private final AdvogadoInteressadoRepository advogadoInteressadoRepository;

    public BuscarDashboardClienteUseCase(CasoJpaRepository casoJpaRepository,
                                         AdvogadoInteressadoRepository advogadoInteressadoRepository) {
        this.casoJpaRepository = casoJpaRepository;
        this.advogadoInteressadoRepository = advogadoInteressadoRepository;
    }

    public DashboardClienteResponse executar(UUID idCliente) {
        List<CasoEntity> casos = casoJpaRepository.findByIdClienteOrderByDataCriacaoDesc(idCliente);

        return new DashboardClienteResponse(
                montarStatusCasos(casos),
                montarCasosPorArea(casos),
                montarHistoricoCasos(casos),
                montarEngajamentoPorCaso(casos)
        );
    }

    private StatusCasosResponse montarStatusCasos(List<CasoEntity> casos) {
        Map<String, Long> totalPorStatus = casos.stream()
                .map(CasoEntity::getStatus)
                .map(this::normalizarStatus)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return new StatusCasosResponse(
                casos.size(),
                totalPorStatus.getOrDefault("EM_ANDAMENTO", 0L),
                totalPorStatus.getOrDefault("ABERTO", 0L),
                totalPorStatus.getOrDefault("ENCERRADO", 0L)
        );
    }

    private List<CasosPorAreaResponse> montarCasosPorArea(List<CasoEntity> casos) {
        return casos.stream()
                .collect(Collectors.groupingBy(caso -> valorOuNaoInformado(caso.getAreaDireito()), Collectors.counting()))
                .entrySet()
                .stream()
                .map(entry -> new CasosPorAreaResponse(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(CasosPorAreaResponse::quantidade).reversed()
                        .thenComparing(CasosPorAreaResponse::area))
                .toList();
    }

    private List<HistoricoCasosResponse> montarHistoricoCasos(List<CasoEntity> casos) {
        YearMonth mesAtual = YearMonth.from(LocalDate.now());
        Map<YearMonth, Long> totalPorMes = casos.stream()
                .filter(caso -> caso.getDataCriacao() != null)
                .collect(Collectors.groupingBy(caso -> YearMonth.from(caso.getDataCriacao()), Collectors.counting()));

        return java.util.stream.IntStream.range(0, QUANTIDADE_MESES_HISTORICO)
                .mapToObj(i -> mesAtual.minusMonths(QUANTIDADE_MESES_HISTORICO - 1L - i))
                .map(mes -> new HistoricoCasosResponse(formatarMes(mes), totalPorMes.getOrDefault(mes, 0L)))
                .toList();
    }

    private List<EngajamentoPorCasoResponse> montarEngajamentoPorCaso(List<CasoEntity> casos) {
        return casos.stream()
                .map(caso -> new EngajamentoPorCasoResponse(
                        caso.getIdCaso(),
                        caso.getTitulo(),
                        caso.getAreaDireito(),
                        advogadoInteressadoRepository.countByCasoId(caso.getIdCaso())
                ))
                .sorted(Comparator.comparing(EngajamentoPorCasoResponse::interessados).reversed()
                        .thenComparing(EngajamentoPorCasoResponse::titulo, Comparator.nullsLast(String::compareToIgnoreCase)))
                .toList();
    }

    private String normalizarStatus(String status) {
        if (status == null || status.isBlank()) {
            return "";
        }

        String semAcento = Normalizer.normalize(status, Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        String statusNormalizado = semAcento.trim()
                .toUpperCase(Locale.ROOT)
                .replace("-", "_")
                .replace(" ", "_");

        if ("EMANDAMENTO".equals(statusNormalizado)) {
            return "EM_ANDAMENTO";
        }

        return statusNormalizado;
    }

    private String valorOuNaoInformado(String valor) {
        if (valor == null || valor.isBlank()) {
            return "Nao informado";
        }

        return valor;
    }

    private String formatarMes(YearMonth mes) {
        String mesFormatado = mes.format(MES_FORMATTER).replace(".", "");

        return mesFormatado.substring(0, 1).toUpperCase(LOCALE_BR) + mesFormatado.substring(1);
    }
}

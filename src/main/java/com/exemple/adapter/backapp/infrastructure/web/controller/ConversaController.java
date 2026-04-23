package com.exemple.adapter.backapp.infrastructure.web.controller;

import com.exemple.adapter.backapp.core.application.dto.command.conversa.CriarConversaCommand;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.ConversaResponse;
import com.exemple.adapter.backapp.core.application.dto.response.conversa.CriarConversaResponse;
import com.exemple.adapter.backapp.core.application.dto.response.mensagem.MensagemResponse;
import com.exemple.adapter.backapp.core.application.usecase.conversa.BuscarConversaPorIdUseCase;
import com.exemple.adapter.backapp.core.application.usecase.conversa.CriarConversaUseCase;
import com.exemple.adapter.backapp.core.application.usecase.conversa.ListarConversasUseCase;
import com.exemple.adapter.backapp.core.application.usecase.mensagem.ListarMensagensPorConversaUseCase;
import com.exemple.adapter.backapp.infrastructure.web.ApiExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conversas")
@Tag(name = "Chat - Conversas", description = "Operacoes de criacao, consulta e listagem de conversas do chat")
@SecurityRequirement(name = "Bearer")
public class ConversaController {

    private final CriarConversaUseCase criarConversaUseCase;
    private final BuscarConversaPorIdUseCase buscarConversaPorIdUseCase;
    private final ListarConversasUseCase listarConversasUseCase;
    private final ListarMensagensPorConversaUseCase listarMensagensPorConversaUseCase;

    public ConversaController(CriarConversaUseCase criarConversaUseCase,
                              BuscarConversaPorIdUseCase buscarConversaPorIdUseCase,
                              ListarConversasUseCase listarConversasUseCase,
                              ListarMensagensPorConversaUseCase listarMensagensPorConversaUseCase) {
        this.criarConversaUseCase = criarConversaUseCase;
        this.buscarConversaPorIdUseCase = buscarConversaPorIdUseCase;
        this.listarConversasUseCase = listarConversasUseCase;
        this.listarMensagensPorConversaUseCase = listarMensagensPorConversaUseCase;
    }

    @PostMapping
    @Operation(
            summary = "Criar conversa",
            description = "Cria uma nova conversa vinculada a um cliente, um advogado e um caso"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Conversa criada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CriarConversaResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "idConversa": "11111111-1111-1111-1111-111111111111",
                                      "idCliente": "22222222-2222-2222-2222-222222222222",
                                      "idAdvogado": "33333333-3333-3333-3333-333333333333",
                                      "idCaso": "44444444-4444-4444-4444-444444444444"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "Ja existe conversa para este cliente, advogado e caso",
                    content = @Content(schema = @Schema(implementation = ApiExceptionHandler.ErroResponse.class))
            )
    })
    public ResponseEntity<CriarConversaResponse> criar(@RequestBody CriarConversaCommand command) {
        CriarConversaResponse response = criarConversaUseCase.executar(command);
        return ResponseEntity.status(201).body(response);
    }

    @GetMapping
    @Operation(
            summary = "Listar conversas",
            description = "Lista as conversas de um cliente, de um advogado ou de um caso. Informe apenas um dos parametros."
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Conversas listadas com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = ConversaResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Parametros invalidos",
                    content = @Content(schema = @Schema(implementation = ApiExceptionHandler.ErroResponse.class))
            )
    })
    public ResponseEntity<List<ConversaResponse>> listar(
            @Parameter(description = "UUID do cliente para listar suas conversas", example = "22222222-2222-2222-2222-222222222222")
            @RequestParam(required = false) UUID clienteId,
            @Parameter(description = "UUID do advogado para listar suas conversas", example = "33333333-3333-3333-3333-333333333333")
            @RequestParam(required = false) UUID advogadoId,
            @Parameter(description = "UUID do caso para listar suas conversas", example = "44444444-4444-4444-4444-444444444444")
            @RequestParam(required = false) UUID casoId) {

        int filtrosInformados = 0;
        if (clienteId != null) {
            filtrosInformados++;
        }
        if (advogadoId != null) {
            filtrosInformados++;
        }
        if (casoId != null) {
            filtrosInformados++;
        }

        if (filtrosInformados != 1) {
            throw new IllegalArgumentException("Informe apenas um entre clienteId, advogadoId ou casoId para listar as conversas");
        }

        if (clienteId != null) {
            return ResponseEntity.ok(listarConversasUseCase.listarPorCliente(clienteId));
        }

        if (advogadoId != null) {
            return ResponseEntity.ok(listarConversasUseCase.listarPorAdvogado(advogadoId));
        }

        return ResponseEntity.ok(listarConversasUseCase.listarPorCaso(casoId));
    }

    @GetMapping("/{idConversa}")
    @Operation(
            summary = "Buscar conversa por id",
            description = "Retorna os dados de uma conversa especifica"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Conversa encontrada",
                    content = @Content(schema = @Schema(implementation = ConversaResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conversa nao encontrada",
                    content = @Content(schema = @Schema(implementation = ApiExceptionHandler.ErroResponse.class))
            )
    })
    public ResponseEntity<ConversaResponse> buscarPorId(@PathVariable UUID idConversa) {
        return ResponseEntity.ok(buscarConversaPorIdUseCase.executar(idConversa));
    }

    @GetMapping("/{idConversa}/mensagens")
    @Operation(
            summary = "Listar mensagens da conversa",
            description = "Retorna o historico de mensagens de uma conversa, ordenado por data de envio"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "200",
                    description = "Mensagens listadas com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = MensagemResponse.class))
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conversa nao encontrada",
                    content = @Content(schema = @Schema(implementation = ApiExceptionHandler.ErroResponse.class))
            )
    })
    public ResponseEntity<List<MensagemResponse>> listarMensagens(@PathVariable UUID idConversa) {
        return ResponseEntity.ok(listarMensagensPorConversaUseCase.executar(idConversa));
    }
}

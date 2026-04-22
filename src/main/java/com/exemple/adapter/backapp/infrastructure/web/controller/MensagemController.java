package com.exemple.adapter.backapp.infrastructure.web.controller;

import com.exemple.adapter.backapp.core.application.dto.command.mensagem.EnviarMensagemCommand;
import com.exemple.adapter.backapp.core.application.dto.response.mensagem.EnviarMensagemResponse;
import com.exemple.adapter.backapp.core.application.usecase.mensagem.EnviarMensagemUseCase;
import com.exemple.adapter.backapp.infrastructure.web.ApiExceptionHandler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mensagens")
@Tag(name = "Chat - Mensagens", description = "Operações de envio de mensagens do chat")
@SecurityRequirement(name = "Bearer")
public class MensagemController {

    private final EnviarMensagemUseCase useCase;

    public MensagemController(EnviarMensagemUseCase useCase) {
        this.useCase = useCase;
    }

    @PostMapping
    @Operation(
            summary = "Enviar mensagem",
            description = "Envia uma nova mensagem para uma conversa existente"
    )
    @ApiResponses(value = {
            @ApiResponse(
                    responseCode = "201",
                    description = "Mensagem enviada com sucesso",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = EnviarMensagemResponse.class),
                            examples = @ExampleObject(value = """
                                    {
                                      "idMensagem": "55555555-5555-5555-5555-555555555555",
                                      "idConversa": "11111111-1111-1111-1111-111111111111",
                                      "conteudo": "Olá, tudo bem?",
                                      "remetenteTipo": "CLIENTE",
                                      "remetenteId": "22222222-2222-2222-2222-222222222222",
                                      "enviadoEm": "2026-04-20T20:45:00"
                                    }
                                    """)
                    )
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Remetente inválido ou payload inválido",
                    content = @Content(schema = @Schema(implementation = ApiExceptionHandler.ErroResponse.class))
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Conversa não encontrada",
                    content = @Content(schema = @Schema(implementation = ApiExceptionHandler.ErroResponse.class))
            )
    })
    public ResponseEntity<EnviarMensagemResponse> enviar(
            @RequestBody EnviarMensagemCommand command) {

        EnviarMensagemResponse response = useCase.executar(command);

        return ResponseEntity.status(201).body(response);
    }
}

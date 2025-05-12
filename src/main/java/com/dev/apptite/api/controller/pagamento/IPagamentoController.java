package com.dev.apptite.api.controller.pagamento;

import com.dev.apptite.api.controller.pagamento.request.PagamentoRequest;
import com.dev.apptite.api.controller.pagamento.response.PagamentoResponse;
import com.dev.apptite.domain.enums.StatusPagamentoEnum;
import com.dev.apptite.domain.exceptions.dto.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Tag(name = "Pagamento")
@RequestMapping("/pagamento")
@Validated
public interface IPagamentoController {

    @Operation(
            summary = "Realizar pagamento",
            description = "Endpoint responsável por realizar pagamento total ou parcial",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Pagamento realizado com sucesso.",
                            content = @Content(schema = @Schema(implementation = PagamentoResponse.class))),
                    @ApiResponse(responseCode = "422", description = "Requisição inválida.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            }
    )
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    ResponseEntity<PagamentoResponse> create(@Valid @RequestBody PagamentoRequest pagamentoRequest);

    @Operation(
            summary = "Consultar status de pagamento",
            description = "Endpoint responsável por consultar o status de pagamento de um cliente",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Status de pagamento retornado com sucesso.",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(responseCode = "404", description = "Cliente não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(responseCode = "500", description = "Erro interno.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            }
    )
    @GetMapping("/{idCliente}/status")
    ResponseEntity<StatusPagamentoEnum> getStatusPagamento(@PathVariable Long idCliente);
}


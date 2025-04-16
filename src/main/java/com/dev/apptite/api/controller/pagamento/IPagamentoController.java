package com.dev.apptite.api.controller.pagamento;

import com.dev.apptite.api.controller.pagamento.request.PagamentoRequest;
import com.dev.apptite.api.controller.pagamento.response.PagamentoResponse;
import com.dev.apptite.domain.exceptions.dto.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@Tag(name = "Pagamento")
@RequestMapping(value = "/pagamento")
@Validated
public interface IPagamentoController {

    @Operation(
            summary = "Relizar pagamento",
            description = "Endpoint responsável por realizar pagamento total ou parcial",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Pagamento realizado com sucesso.",
                            content = @Content(schema = @Schema(implementation = PagamentoResponse.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @PostMapping
    @ResponseStatus(CREATED)
    ResponseEntity<PagamentoResponse> create(@Valid @RequestBody PagamentoRequest pagamentoRequest);
}

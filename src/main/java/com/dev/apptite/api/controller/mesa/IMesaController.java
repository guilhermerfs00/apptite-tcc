package com.dev.apptite.api.controller.mesa;

import com.dev.apptite.api.controller.mesa.request.MesaRequest;
import com.dev.apptite.api.controller.mesa.response.MesaResponse;
import com.dev.apptite.domain.exceptions.dto.ErrorDTO;
import com.dev.apptite.domain.utils.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@Tag(name = "Mesas")
@RequestMapping(value = "/mesas")
@Validated
public interface IMesaController {

    @Operation(
            summary = "Criar Mesa",
            description = "Endpoint responsável por criar uma nova mesa",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Restaurante criado com sucesso.",
                            content = @Content(schema = @Schema(implementation = MesaResponse.class))),
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
    ResponseEntity<MesaResponse> create(@Valid @RequestBody MesaRequest mesaRequest);

    @Operation(
            summary = "Buscar mesa por id",
            description = "Endpoint responsável por buscar uma mesa",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Mesa encontrada com sucesso.",
                            content = @Content(schema = @Schema(implementation = MesaResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Mesa não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("{id}")
    @ResponseStatus(OK)
    ResponseEntity<MesaResponse> findById(@PathVariable Long id);

    @Operation(
            summary = "Buscar mesas por ID do Restaurante",
            description = "Endpoint responsável por buscar todas as mesas de um Restaurante com paginação",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Mesas encontradas com sucesso.",
                            content = @Content(schema = @Schema(implementation = MesaResponse.class))
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Nenhuma mesa encontrada para este restaurante.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))
                    ),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))
                    )
            }
    )
    @GetMapping("restaurante/{idRestaurante}")
    @ResponseStatus(OK)
    ResponseEntity<PageResponse<MesaResponse>> findMesasByRestaurante(
            @PathVariable Long idRestaurante,
            @RequestParam(defaultValue = "0") @Min(0) int pageNumber,
            @RequestParam(defaultValue = "10") @Min(1) int pageSize
    );


    @Operation(
            summary = "Deletar Mesa",
            description = "Endpoint responsável por deletar uma mesa",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Mesa deletada com sucesso."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Mesa não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    ResponseEntity<Void> delete(@PathVariable Long id);
}

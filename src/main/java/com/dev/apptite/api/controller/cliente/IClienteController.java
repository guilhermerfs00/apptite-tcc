package com.dev.apptite.api.controller.cliente;

import com.dev.apptite.api.controller.cliente.request.ClienteRequest;
import com.dev.apptite.api.controller.cliente.response.ClienteResponse;
import com.dev.apptite.domain.exceptions.dto.ErrorDTO;
import com.dev.apptite.domain.utils.PageResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@Tag(name = "Clientes")
@RequestMapping(value = "/clientes")
@Validated
public interface IClienteController {

    @Operation(
            summary = "Criar Cliente",
            description = "Endpoint responsável por criar um novo cliente",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Cliente criado com sucesso.",
                            content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
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
    ResponseEntity<ClienteResponse> create(@Valid @RequestBody ClienteRequest clienteRequest);

    @Operation(
            summary = "Buscar cliente por id",
            description = "Endpoint responsável por buscar um cliente por id",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cliente encontrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("{id}")
    @ResponseStatus(OK)
    ResponseEntity<ClienteResponse> findById(@PathVariable Long id);

    @Operation(
            summary = "Deletar Cliente",
            description = "Endpoint responsável por deletar um cliente",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Cliente deletado com sucesso."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @DeleteMapping(value = "/{id}")
    @ResponseStatus(NO_CONTENT)
    ResponseEntity<Void> delete(@PathVariable Long id);

    @Operation(
            summary = "Consultar cliente paginado",
            description = "Endpoint responsável por buscar um cliente paginado",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Cliente encontrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = ClienteResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Cliente não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping
    @ResponseStatus(OK)
    ResponseEntity<PageResponse<ClienteResponse>> findAllPaginated(
            @ParameterObject @RequestParam(defaultValue = "0") @Min(0) int page,
            @ParameterObject @RequestParam(defaultValue = "10") @Min(1) int size);
}
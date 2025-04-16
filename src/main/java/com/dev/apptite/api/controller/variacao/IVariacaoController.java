package com.dev.apptite.api.controller.variacao;

import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
import com.dev.apptite.api.controller.variacao.request.VariacaoRequest;
import com.dev.apptite.api.controller.variacao.response.VariacaoResponse;
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
@Tag(name = "Variações")
@RequestMapping(value = "/variacoes")
@Validated
public interface IVariacaoController {

    @Operation(
            summary = "Criar Variação",
            description = "Endpoint responsável por criar uma nova variação",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Variação criada com sucesso.",
                            content = @Content(schema = @Schema(implementation = VariacaoResponse.class))),
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
    ResponseEntity<VariacaoResponse> create(@Valid @RequestBody VariacaoRequest variacaoRequest);

    @Operation(
            summary = "Buscar variação por id",
            description = "Endpoint responsável por buscar uma variação",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Variação encontrada com sucesso.",
                            content = @Content(schema = @Schema(implementation = VariacaoResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Variação não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("{id}")
    @ResponseStatus(OK)
    ResponseEntity<VariacaoResponse> findById(@PathVariable Long id);

    @Operation(
            summary = "Deletar variação",
            description = "Endpoint responsável por deletar uma variação",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Variação deletada com sucesso."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Variação não encontrada.",
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
            summary = "Consultar variação paginada",
            description = "Endpoint responsável por buscar uma variação paginada",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Variação encontrada com sucesso.",
                            content = @Content(schema = @Schema(implementation = VariacaoResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Variação não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping
    @ResponseStatus(OK)
    ResponseEntity<PageResponse<VariacaoResponse>> findAllPaginated(
            @ParameterObject @RequestParam(defaultValue = "0") @Min(0) int page,
            @ParameterObject @RequestParam(defaultValue = "10") @Min(1) int size);

    @Operation(
            summary = "Buscar categoria por id do Restaurante",
            description = "Endpoint responsável por buscar categorias de um Restaurante com paginação",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Categoria encontrada com sucesso.",
                            content = @Content(schema = @Schema(implementation = CategoriaResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Categoria não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("item/{idItem}")
    @ResponseStatus(OK)
    ResponseEntity<PageResponse<VariacaoResponse>> findByIdItem(
            @PathVariable Long idItem,
            @RequestParam(defaultValue = "0") @Min(0) int pageNumber,
            @RequestParam(defaultValue = "10") @Min(1) int pageSize);
}
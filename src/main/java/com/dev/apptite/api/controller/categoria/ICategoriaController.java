package com.dev.apptite.api.controller.categoria;

import com.dev.apptite.api.controller.categoria.request.CategoriaRequest;
import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
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
@Tag(name = "Categorias")
@RequestMapping(value = "/categorias")
@Validated
public interface ICategoriaController {

    @Operation(
            summary = "Criar Categoria",
            description = "Endpoint responsável por criar uma nova categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Categoria criada com sucesso.",
                            content = @Content(schema = @Schema(implementation = CategoriaResponse.class))),
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
    ResponseEntity<CategoriaResponse> create(@Valid @RequestBody CategoriaRequest categoriaRequest);

    @Operation(
            summary = "Buscar categoria por id",
            description = "Endpoint responsável por buscar uma categoria",
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
    @GetMapping("{id}")
    @ResponseStatus(OK)
    ResponseEntity<CategoriaResponse> findById(@PathVariable Long id);

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
    @GetMapping("restaurante/{idRestaurante}")
    @ResponseStatus(OK)
    ResponseEntity<PageResponse<CategoriaResponse>> findByIdRestaurante(
            @PathVariable Long idRestaurante,
            @RequestParam(defaultValue = "0") @Min(0) int pageNumber,
            @RequestParam(defaultValue = "10") @Min(1) int pageSize);

    @Operation(
            summary = "Deletar Categoria",
            description = "Endpoint responsável por deletar uma categoria",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Categoria deletada com sucesso."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Categoria não encontrada.",
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
            summary = "Consultar categoria paginada",
            description = "Endpoint responsável por buscar uma categoria paginada",
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
    @GetMapping
    @ResponseStatus(OK)
    ResponseEntity<PageResponse<CategoriaResponse>> findAllPaginated(
            @ParameterObject @RequestParam(defaultValue = "0") @Min(0) int page,
            @ParameterObject @RequestParam(defaultValue = "10") @Min(1) int size);
}
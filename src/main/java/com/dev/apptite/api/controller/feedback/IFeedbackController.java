package com.dev.apptite.api.controller.feedback;

import com.dev.apptite.api.controller.feedback.request.FeedbackRequest;
import com.dev.apptite.api.controller.feedback.request.FeedbackUpdateRequest;
import com.dev.apptite.api.controller.feedback.response.FeedbackResponse;
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
@Tag(name = "Feedbacks")
@RequestMapping(value = "/feedbacks")
@Validated
public interface IFeedbackController {

    @Operation(
            summary = "Criar Feedback",
            description = "Endpoint responsável por criar um novo feedback",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Feedback criado com sucesso.",
                            content = @Content(schema = @Schema(implementation = FeedbackResponse.class))),
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
    ResponseEntity<FeedbackResponse> create(@Valid @RequestBody FeedbackRequest feedbackRequest);

    @Operation(
            summary = "Buscar feedback por id",
            description = "Endpoint responsável por buscar um feedback",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Feedback encontrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = FeedbackResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Feedback não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("{id}")
    @ResponseStatus(OK)
    ResponseEntity<FeedbackResponse> findById(@PathVariable Long id);

    @Operation(
            summary = "Deletar Feedback",
            description = "Endpoint responsável por deletar um feedback",
            responses = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Feedback deletado com sucesso."),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Feedback não encontrado.",
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
            summary = "Consultar feedback paginado",
            description = "Endpoint responsável por buscar um feedback paginado",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Feedback encontrado com sucesso.",
                            content = @Content(schema = @Schema(implementation = FeedbackResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Feedback não encontrado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping
    @ResponseStatus(OK)
    ResponseEntity<PageResponse<FeedbackResponse>> findAllPaginated(
            @ParameterObject @RequestParam(defaultValue = "0") @Min(0) int page,
            @ParameterObject @RequestParam(defaultValue = "10") @Min(1) int size);

    @Operation(
            summary = "Responder feedback",
            description = "Endpoint responsável por responde um feedback",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Resposta enviada com sucesso.",
                            content = @Content(schema = @Schema(implementation = FeedbackResponse.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @PostMapping("{id}")
    @ResponseStatus(CREATED)
    ResponseEntity<FeedbackResponse> responderFeedback(@Valid @PathVariable Long id, @RequestBody FeedbackUpdateRequest request);
}
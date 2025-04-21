package com.dev.apptite.api.controller.auth;

import com.dev.apptite.api.controller.auth.request.LoginRequest;
import com.dev.apptite.domain.exceptions.dto.ErrorDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.*;

@RestController
@Tag(name = "Auth")
@RequestMapping(value = "/auth")
@Validated
public interface IAuthController {

    @Operation(
            summary = "Login do cliente",
            description = "Endpoint responsável por autenticar um cliente com base no ID fornecido",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autenticação bem-sucedida.",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Credenciais inválidas.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @PostMapping("/login/cliente/{idCliente}")
    @ResponseStatus(OK)
    String loginCliente(@PathVariable Long idCliente);

    @Operation(
            summary = "Login",
            description = "Endpoint responsável por autenticar um usuário",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autenticação bem-sucedida.",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Credenciais inválidas.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @PostMapping("/login")
    @ResponseStatus(OK)
    String login(@Valid @RequestBody LoginRequest request);

    @Operation(
            summary = "Extrair nome de usuário",
            description = "Endpoint responsável por extrair o nome de usuário a partir de um token",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Nome de usuário extraído com sucesso.",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Token inválido.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("/username/{token}")
    @ResponseStatus(OK)
    String extractUsername(@PathVariable String token);

    @Operation(
            summary = "Obter usuário autenticado",
            description = "Endpoint responsável por obter o usuário autenticado",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Usuário autenticado obtido com sucesso.",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(
                            responseCode = "401",
                            description = "Usuário não autenticado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("/user")
    @ResponseStatus(OK)
    String getUser(Authentication authentication);

    @Operation(
            summary = "Acesso apenas para administradores",
            description = "Endpoint acessível apenas para administradores",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Acesso permitido.",
                            content = @Content(schema = @Schema(implementation = String.class))),
                    @ApiResponse(
                            responseCode = "403",
                            description = "Acesso negado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @Secured("ADMIN")
    @GetMapping("/admin")
    @ResponseStatus(OK)
    String onlyAdmin(Authentication authentication);
}
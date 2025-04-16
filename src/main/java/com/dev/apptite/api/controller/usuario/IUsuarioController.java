package com.dev.apptite.api.controller.usuario;

import com.dev.apptite.api.controller.auth.request.LoginRequest;
import com.dev.apptite.api.controller.pedido.response.PedidoResponse;
import com.dev.apptite.api.controller.usuario.request.AuthenticationRequest;
import com.dev.apptite.api.controller.usuario.request.RecoveryTokenRequest;
import com.dev.apptite.api.controller.usuario.request.UserRequest;
import com.dev.apptite.api.controller.usuario.request.UserValidateRequest;
import com.dev.apptite.api.controller.usuario.response.TokenResponse;
import com.dev.apptite.api.controller.usuario.response.UserResponse;
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
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RestController
@Tag(name = "Usuarios")
@RequestMapping(value = "/usuarios")
@Validated
public interface IUsuarioController {

    @Operation(summary = "Criar um novo usuário", description = "Adicionar um novo usuário no sistema",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuario criado com sucesso.",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @PostMapping("/create")
    ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest request);

    @Operation(
            summary = "Buscar pedido por id do Restaurante",
            description = "Endpoint responsável por buscar pedidos de um Restaurante com paginação",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Pedido encontrada com sucesso.",
                            content = @Content(schema = @Schema(implementation = PedidoResponse.class))),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Pedido não encontrada.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("/find-by-email")
    @ResponseStatus(OK)
    ResponseEntity<UserResponse> findByEmail(@RequestParam String email);

    @Operation(summary = "Criar um novo usuário", description = "Adicionar um novo usuário no sistema",
            responses = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Usuario criado com sucesso.",
                            content = @Content(schema = @Schema(implementation = UserResponse.class))),
                    @ApiResponse(
                            responseCode = "422",
                            description = "Requisição possui pelo menos um valor faltante ou inválido.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class))),
                    @ApiResponse(
                            responseCode = "500",
                            description = "Ocorreu um erro inesperado.",
                            content = @Content(schema = @Schema(implementation = ErrorDTO.class)))
            })
    @GetMapping("/isValid")
    ResponseEntity<Boolean> isValidUser(@RequestParam String email,
                                        @RequestParam String senha);

    @Operation(summary = "Autenticar usuário", description = "Autenticar um usuário e retornar um token JWT",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Autenticação bem-sucedida.",
                            content = @Content(schema = @Schema(implementation = TokenResponse.class))),
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
    ResponseEntity<TokenResponse> login(@Valid @RequestBody AuthenticationRequest request);

}
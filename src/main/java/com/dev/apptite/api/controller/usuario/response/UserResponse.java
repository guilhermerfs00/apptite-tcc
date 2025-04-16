package com.dev.apptite.api.controller.usuario.response;

import com.dev.apptite.domain.enums.RoleEnum;
import lombok.*;

import java.time.LocalDateTime;

@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Value
@Builder
public class UserResponse {
    Long idRestaurante;
    Long idUsuario;
    String nome;
    String email;
    String senha;
    RoleEnum role;
    LocalDateTime dataCriacao;
    LocalDateTime dataAtualizacao;
}
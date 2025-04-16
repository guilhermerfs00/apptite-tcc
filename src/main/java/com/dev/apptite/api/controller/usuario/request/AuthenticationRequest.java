package com.dev.apptite.api.controller.usuario.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationRequest {

    @Schema(description = "Email do usuário", example = "guilherme@gmail.com")
    private String email;

    @Schema(description = "Senha do usuário", example = "123")
    private String password;

}

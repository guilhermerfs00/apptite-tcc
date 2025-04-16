package com.dev.apptite.api.controller.usuario.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Usuario Request")
public class UserValidateRequest {

    @Schema(description = "Email do usuário", example = "g@g.com")
    String email;

    @NotNull
    @NotEmpty
    @Schema(description = "Senha do usuário", example = "123")
    String senha;
}

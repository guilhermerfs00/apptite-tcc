package com.dev.apptite.api.controller.usuario.request;

import com.dev.apptite.domain.enums.RoleEnum;
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
public class UserRequest {

    Long idRestaurante;

    @Schema(description = "Email do usuário", example = "guilherme@gmail.com")
    String nome;

    @Schema(description = "Email do usuário", example = "guilherme@gmail.com")
    String email;

    @NotNull
    @NotEmpty
    @Schema(description = "Senha do usuário", example = "123")
    String senha;

    @Schema(description = "Role", example = "ADMIN")
    RoleEnum role;
}

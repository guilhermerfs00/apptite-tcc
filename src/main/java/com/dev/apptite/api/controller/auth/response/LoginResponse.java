package com.dev.apptite.api.controller.auth.response;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class LoginResponse {

    @Schema(description = "Usuario", example = "admin")
    @NotNull
    private String username;

    @Schema(description = "Senha", example = "123")
    @NotNull
    private Long password;
}
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
@Schema(description = "RecoveryToken Request")
public class RecoveryTokenRequest {


    @NotNull
    @NotEmpty
    @Schema(description = "Nova senha do usuário", example = "111")
    String newPassword;
    String recoveryToken;
}

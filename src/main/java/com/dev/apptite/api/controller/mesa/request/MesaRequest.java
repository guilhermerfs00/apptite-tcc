package com.dev.apptite.api.controller.mesa.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class MesaRequest {

    @Schema(description = "Id do restaurante",example = "1")
    @NotNull
    private Long idRestaurante;

    @Schema(description = "Número da Mesa", example = "10")
    @NotNull
    private int numero;

}

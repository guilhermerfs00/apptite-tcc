package com.dev.apptite.api.controller.restaurante.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class RestauranteRequest {

    @Schema(description = "Nome do restaurante", example = "Churras")
    @NotNull
    private String nome;

    @Schema(description = "Endereço", example = "Avenida Miguel Perrela, 69")
    @NotNull
    private String endereco;

}

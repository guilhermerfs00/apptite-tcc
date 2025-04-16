package com.dev.apptite.api.controller.restaurante.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class RestauranteUpdateRequest {

    @Schema(description = "Nome do restaurante", example = "Churras")
    private String nome;

    @Schema(description = "Endereço", example = "Avenida Miguel Perrela, 69")
    private String endereco;

}

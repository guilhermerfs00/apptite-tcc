package com.dev.apptite.api.controller.item.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ItemUpdateRequest {

    @Schema(description = "Id do restaurante", example = "1")
    private String id;

    @Schema(description = "Nome da categoria", example = "Comidas")
    @NotNull
    private String nome;

    @Schema(description = "Identificador do restaurante", example = "1")
    @NotNull
    private Long idRestaurante;
}

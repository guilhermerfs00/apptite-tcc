package com.dev.apptite.api.controller.categoria.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class CategoriaUpdateRequest {

    @Schema(description = "Id da categoria", example = "1")
    private String id;

    @Schema(description = "Nome da categoria", example = "Comidas")
    @NotNull
    private String nome;
}

package com.dev.apptite.api.controller.variacao.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class VariacaoRequest {

    @Schema(description = "Nome da variação", example = "Acréscimo de bacon")
    @NotNull
    private String nome;

    @Schema(description = "Identificador do item", example = "1")
    @NotNull
    private Long idItem;

    @Schema(description = "Preço da variação", example = "3.50")
    @NotNull
    private Double preco;

}
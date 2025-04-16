package com.dev.apptite.api.controller.item.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ItemRequest {

    @Schema(description = "Nome do item", example = "Hamburguer")
    @NotNull
    private String nome;

    @Schema(description = "Identificador da categoria", example = "1")
    @NotNull
    private Long idCategoria;

    @Schema(description = "Preço do item", example = "25.90")
    @NotNull
    private double preco;

    @Schema(description = "Descrição do item", example = "Pão com carne,queijo, salada e molho especial")
    @NotNull
    private String descricao;
}
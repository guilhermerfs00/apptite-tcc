package com.dev.apptite.api.controller.item.response;

import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ItemResponse {

    Long idItem;
    String nome;
    CategoriaResponse categoria;
    private String descricao;
    private double preco;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

}

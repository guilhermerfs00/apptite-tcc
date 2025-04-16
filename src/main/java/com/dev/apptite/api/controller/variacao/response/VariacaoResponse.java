package com.dev.apptite.api.controller.variacao.response;

import com.dev.apptite.api.controller.item.response.ItemResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VariacaoResponse {

    Long idVariacao;
    String nome;
    ItemResponse item;
    double preco;
    LocalDateTime dataCriacao;
    LocalDateTime dataAtualizacao;

}

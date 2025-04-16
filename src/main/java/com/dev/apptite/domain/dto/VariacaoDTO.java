package com.dev.apptite.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class VariacaoDTO {

    private Long idVariacao;
    private String nome;
    private Double preco;
    private Long idItem;
    private ItemDTO item;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
package com.dev.apptite.domain.dto;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RestauranteDTO {

    private Long idRestaurante;
    private String nome;
    private String endereco;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

}
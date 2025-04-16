package com.dev.apptite.api.controller.restaurante.response;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class RestauranteResponse {
    Long idRestaurante;
    String nome;
    String endereco;
    LocalDateTime dataCriacao;
    LocalDateTime dataAtualizacao;
}

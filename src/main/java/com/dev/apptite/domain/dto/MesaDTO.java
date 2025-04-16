package com.dev.apptite.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MesaDTO {

    private Long idMesa;
    private Long idRestaurante;
    private int numero;
    private String uuid;
    private RestauranteDTO restaurante;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

}

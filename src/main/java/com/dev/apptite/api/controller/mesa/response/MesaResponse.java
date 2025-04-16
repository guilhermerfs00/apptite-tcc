package com.dev.apptite.api.controller.mesa.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MesaResponse {

    Long idMesa;
    int numero;
    String uuid;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}

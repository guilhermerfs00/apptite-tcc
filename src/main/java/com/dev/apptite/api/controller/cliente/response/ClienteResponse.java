package com.dev.apptite.api.controller.cliente.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteResponse {

    Long idCliente;
    String nome;
    String celular;
    String cpf;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

}
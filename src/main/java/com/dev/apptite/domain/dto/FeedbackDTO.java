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
public class FeedbackDTO {

    private Long idFeedback;
    private Long idCliente;
    private String conteudo;
    private Long nota;
    private ClienteDTO cliente;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

}

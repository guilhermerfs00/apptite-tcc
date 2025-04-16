package com.dev.apptite.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackDTO {

    private Long idFeedback;
    private Long idCliente;
    private Long idUsuario;
    private List<MensagemDTO> mensagem;
    private String conteudo;
    private ClienteDTO cliente;

}

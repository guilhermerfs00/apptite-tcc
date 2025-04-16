package com.dev.apptite.domain.dto;

import com.dev.apptite.domain.enums.TipoMensagemEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MensagemDTO {

    private Long idMensagem;
    private TipoMensagemEnum tipoMensagem;
    private FeedbackDTO feedback;
    private String conteudo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}

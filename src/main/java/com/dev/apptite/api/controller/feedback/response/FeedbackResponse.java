package com.dev.apptite.api.controller.feedback.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class FeedbackResponse {

    private Long idFeedback;
    private String conteudo;
    private Long nota;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

}

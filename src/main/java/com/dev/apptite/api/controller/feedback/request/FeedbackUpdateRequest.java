package com.dev.apptite.api.controller.feedback.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class FeedbackUpdateRequest {


    @Schema(description = "Identificador do usuario", example = "1")
    private Long idUsuario;

    @Schema(description = "Mensagem de feedback", example = "Obrigado pelo feedback!")
    private String resposta;
}
package com.dev.apptite.api.controller.feedback.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class FeedbackRequest {

    @Schema(description = "Identificador do cliente", example = "1")
    private Long idCliente;

    @Schema(description = "Mensagem de feedback", example = "Hambúrguer estava ótimo")
    private String conteudo;

    @Schema(description = "Nota feedback", example = "5")
    private Long nota;

}
package com.dev.apptite.api.controller.cliente.request;

import com.dev.apptite.domain.enums.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class ClienteRequest {

    @Schema(description = "Nome", example = "Guilherme")
    @NotNull
    private String nome;

    @Schema(description = "Celular", example = "31984049466")
    @NotNull
    private String celular;

    @Schema(description = "CPF", example = "018954589321")
    @NotNull
    private String cpf;

    @Schema(description = "Identificador da mesa", example = "1")
    @NotNull
    private Long idMesa;

}
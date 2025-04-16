package com.dev.apptite.api.controller.pagamento.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class PagamentoRequest {

    @Schema(description = "Mesa", example = "1")
    @NotNull
    private Long idMesa;

    private BigDecimal valorTotal;

    @Schema(description = "Valor pago", example = "2,00")
    @NotNull
    private BigDecimal valorPago;
}

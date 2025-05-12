package com.dev.apptite.api.controller.pagamento.request;

import com.dev.apptite.domain.entity.MetodoDePagamento;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagamentoRequest {

    @NotNull
    private Long idPagamento;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal valorPago;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal valorTotal;

    @NotNull
    private Long idCliente;

    @NotNull
    private MetodoDePagamento metodoDePagamento;
}

package com.dev.apptite.api.controller.pagamento.response;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PagamentoResponse {

    private Long idPagamento;
    private BigDecimal valorPago;
    private BigDecimal valorTotal;
    private String status;
    private String stripePaymentIntentId;
    private String clientSecret;
}

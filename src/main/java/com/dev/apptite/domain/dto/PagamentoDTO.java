package com.dev.apptite.domain.dto;

import com.dev.apptite.domain.entity.MetodoDePagamento;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {

    private Long idPagamento;
    private BigDecimal valorPago;
    private BigDecimal valorTotal;
    private Long idCliente;
    private ClienteDTO cliente;
    private String status;
    private String stripePaymentIntentId;
    private String clientSecret;

    private MetodoDePagamento metodoDePagamento;

}


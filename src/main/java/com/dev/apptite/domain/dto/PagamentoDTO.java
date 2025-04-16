package com.dev.apptite.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PagamentoDTO {

    private Long idMesa;
    private BigDecimal valorTotal;
    private BigDecimal valorPago;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}

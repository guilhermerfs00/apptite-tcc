package com.dev.apptite.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum StatusPedidoEnum {

    PEDIDO_REALIZADO,
    PRODUZINDO_PEDIDO,
    AGUARDANDO_RETIRADA,
    AGUARDANDO_PAGAMENTO,
    PROCESSANDO_PAGAMENTO,
    PAGAMENTO_RECUSADO,
    PAGAMENTO_CONCLUIDO;

}

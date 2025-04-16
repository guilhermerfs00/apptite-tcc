package com.dev.apptite.api.controller.pedido.request;

import com.dev.apptite.domain.enums.StatusPedidoEnum;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PedidoUpdateRequest {

    StatusPedidoEnum statusPedidoEnum;

}
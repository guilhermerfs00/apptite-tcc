package com.dev.apptite.api.controller.pedido.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class PedidoRequest {

    @Schema(description = "IDs variacao", example = "[1,2,3]")
    private List<Long> idsVariacao;

    @Schema(description = "ID item", example = "1")
    private Long idItem;

    @Schema(description = "Identificador do cliente", example = "1")
    private Long idCliente;

}
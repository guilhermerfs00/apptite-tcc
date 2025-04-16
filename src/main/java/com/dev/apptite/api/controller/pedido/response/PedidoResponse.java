package com.dev.apptite.api.controller.pedido.response;

import com.dev.apptite.api.controller.cliente.response.ClienteResponse;
import com.dev.apptite.api.controller.item.response.ItemResponse;
import com.dev.apptite.api.controller.variacao.response.VariacaoResponse;
import com.dev.apptite.domain.enums.StatusPedidoEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PedidoResponse {

    private Long idPedido;
    private ClienteResponse cliente;
    private StatusPedidoEnum status;
    private List<VariacaoResponse> variacoes;
    private List<ItemResponse> itens;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

}
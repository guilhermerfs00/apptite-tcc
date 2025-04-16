package com.dev.apptite.domain.dto;

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
public class PedidoDTO {

    private Long idPedido;
    private Long idCliente;
    private ClienteDTO cliente;
    private StatusPedidoEnum status;
    private List<Long> idsVariacao;
    private List<VariacaoDTO> variacoes;
    private Long idItem;
    private List<ItemDTO> itens;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}
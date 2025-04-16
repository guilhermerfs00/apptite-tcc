package com.dev.apptite.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ClienteDTO {

    private Long idCliente;
    private String nome;
    private String celular;
    private String cpf;
    private Long idMesa;
    private MesaDTO mesa;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    @JsonIgnore
    private List<PedidoDTO> pedidos;
}

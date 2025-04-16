package com.dev.apptite.domain.dto;

import com.dev.apptite.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long idRestaurante;
    private RestauranteDTO restaurante;
    private Long idUsuario;
    private String nome;
    private String email;
    private String senha;
    private RoleEnum role;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}

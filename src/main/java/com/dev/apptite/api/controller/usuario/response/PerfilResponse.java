package com.dev.apptite.api.controller.usuario.response;

import com.dev.apptite.domain.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilResponse {
    private Long id;
    private String nome;
    private String email;
    private RoleEnum role;
    private Long idRestaurante;
}
package com.dev.apptite.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerfilDTO {
    private String name;
    private String email;
    private Boolean active;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}

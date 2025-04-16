package com.dev.apptite.domain.dto;

import jakarta.persistence.Column;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CategoriaDTO {

    private Long idCategoria;
    private String nome;
    private Long idRestaurante;
    private RestauranteDTO restaurante;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}

package com.dev.apptite.domain.dto;

import com.dev.apptite.domain.enums.CriteriaTypeEnum;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
public class CriteriaOperatorsDTO {

    private String field;
    private CriteriaTypeEnum operator;
    private String value;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;
}

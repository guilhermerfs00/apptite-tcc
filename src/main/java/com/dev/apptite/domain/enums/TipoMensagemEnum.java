package com.dev.apptite.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum TipoMensagemEnum {

    PERGUNTA("PERGUNTA"),
    RESPOSTA("RESPOSTA");

    private final String value;
}

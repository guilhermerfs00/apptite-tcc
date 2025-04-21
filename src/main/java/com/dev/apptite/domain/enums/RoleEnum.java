package com.dev.apptite.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum RoleEnum {

    ADMIN("ADMIN"),
    CHEF("CHEF"),
    GARCON("GARCON"),
    CLIENTE("CLIENTE");

    private final String value;
}

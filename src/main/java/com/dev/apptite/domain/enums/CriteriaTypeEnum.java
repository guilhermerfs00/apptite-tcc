package com.dev.apptite.domain.enums;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum CriteriaTypeEnum {

    LIKE,
    EQUAL,
    BTW,
    GT,
    LT,
    GTE,
    LTE,
    IN;
    public static CriteriaTypeEnum fromString(String type) {
        return CriteriaTypeEnum.valueOf(type.toUpperCase());
    }

}

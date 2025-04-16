package com.dev.apptite.domain.exceptions.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@Builder
public class ErrorDTO {
    private final String message;
}

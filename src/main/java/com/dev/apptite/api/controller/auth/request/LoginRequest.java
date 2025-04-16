package com.dev.apptite.api.controller.auth.request;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class LoginRequest {
    private String username;
    private String password;
}
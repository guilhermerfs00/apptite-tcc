package com.dev.apptite.api.controller.auth;

import com.dev.apptite.api.controller.auth.request.LoginRequest;
import com.dev.apptite.api.controller.usuario.response.PerfilResponse;
import com.dev.apptite.domain.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AuthController implements IAuthController {

    private final AuthService authService;

    @Override
    public String login(LoginRequest request) {
        return authService.generateToken(request.getUsername(), null);
    }

    @Override
    public String extractUsername(String token) {
        return authService.extractUsername(token);
    }

    @Override
    public String getUser(Authentication authentication) {
        return "User: " + authentication.getName();
    }

    @Override
    public String onlyAdmin(Authentication authentication) {
        return "Admin: " + authentication.getName();
    }
}

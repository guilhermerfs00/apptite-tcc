package com.dev.apptite.domain.service;

import com.dev.apptite.api.controller.usuario.response.PerfilResponse;
import com.dev.apptite.domain.security.JwtUtil;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    public String generateToken(String username, PerfilResponse profile) {
        return JwtUtil.generateToken(username, profile);
    }

    public String extractUsername(String token) {
        return JwtUtil.extractUsername(token);
    }
}


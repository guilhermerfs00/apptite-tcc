package com.dev.apptite.domain.service;

import com.dev.apptite.api.controller.usuario.response.PerfilResponse;
import com.dev.apptite.domain.dto.ClienteDTO;
import com.dev.apptite.domain.enums.RoleEnum;
import com.dev.apptite.domain.security.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@AllArgsConstructor
public class AuthService {

    private final RestauranteService restauranteService;
    private final ClienteService clienteService;

    public String generateToken(String username, PerfilResponse profile) {
        return JwtUtil.generateToken(username, profile);
    }

    public String generateClientToken(Long idCliente) {
        CompletableFuture<ClienteDTO> clienteFuture = CompletableFuture.supplyAsync(() -> clienteService.buscarPorId(idCliente));
        CompletableFuture<Long> restauranteFuture = CompletableFuture.supplyAsync(() -> restauranteService.findByIdCliente(idCliente).getIdRestaurante());

        ClienteDTO clienteDTO = clienteFuture.join();
        Long idRestaurante = restauranteFuture.join();

        var profile = PerfilResponse.builder()
                .id(idCliente)
                .idRestaurante(idRestaurante)
                .nome(clienteDTO.getNome())
                .role(RoleEnum.CLIENTE)
                .email(clienteDTO.getEmail())
                .build();

        return JwtUtil.generateToken(String.valueOf(clienteDTO.getIdCliente()), profile);
    }

    public String extractUsername(String token) {
        return JwtUtil.extractUsername(token);
    }
}


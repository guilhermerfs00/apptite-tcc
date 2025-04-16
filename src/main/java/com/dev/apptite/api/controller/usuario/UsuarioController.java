package com.dev.apptite.api.controller.usuario;

import com.dev.apptite.api.controller.pedido.response.PedidoResponse;
import com.dev.apptite.api.controller.usuario.request.AuthenticationRequest;
import com.dev.apptite.api.controller.usuario.request.UserRequest;
import com.dev.apptite.api.controller.usuario.request.UserValidateRequest;
import com.dev.apptite.api.controller.usuario.response.TokenResponse;
import com.dev.apptite.api.controller.usuario.response.UserResponse;
import com.dev.apptite.api.controller.usuario.response.LoginResponse;
import com.dev.apptite.domain.dto.UsuarioDTO;
import com.dev.apptite.domain.enums.RoleEnum;
import com.dev.apptite.domain.mapper.UsuarioMapper;
import com.dev.apptite.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class UsuarioController implements IUsuarioController {

    private final UsuarioService service;
    private final UsuarioMapper mapper;

    @Override
    public ResponseEntity<UserResponse> createUser(UserRequest request) {
        UsuarioDTO response = service.salvar(mapper.requestToDto(request));
        return ResponseEntity.status(CREATED).body(mapper.dtoToResponse(response));
    }

    @Override
    public ResponseEntity<UserResponse> findByEmail(String email) {
        UsuarioDTO response = service.findByEmail(email);
        return ResponseEntity.status(OK).body(mapper.dtoToResponse(response));
    }

    @Override
    public ResponseEntity<Boolean> isValidUser(String email, String senha) {
        Boolean response = service.idValidUser(email, senha);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<TokenResponse> login(AuthenticationRequest request) {
        var response = service.login(request.getEmail(), request.getPassword());
        return ResponseEntity.status(OK).body(response);
    }
}
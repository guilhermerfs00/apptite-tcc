package com.dev.apptite.domain.service;

import com.dev.apptite.api.controller.usuario.response.PerfilResponse;
import com.dev.apptite.api.controller.usuario.response.TokenResponse;
import com.dev.apptite.domain.dto.UsuarioDTO;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.UsuarioMapper;
import com.dev.apptite.repository.impl.IUsuarioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsuarioService {

    private final IUsuarioRepository repository;
    private final UsuarioMapper mapper;
    private final PasswordEncoder passwordEncoder;
    private final AuthService authService;
    private final RestauranteService restauranteService;

    public UsuarioDTO findById(Long id) {
        return repository.findById(id)
                .map(mapper::entityToBo)
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
    }

    public List<UsuarioDTO> findAll() {
        return repository.findAll()
                .parallelStream() // Processamento paralelo
                .map(mapper::entityToBo)
                .collect(Collectors.toList());
    }

    public UsuarioDTO salvar(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.getIdRestaurante() != null) {
            usuarioDTO.setRestaurante(restauranteService.findById(usuarioDTO.getIdRestaurante()));
        }
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        return mapper.entityToBo(repository.save(mapper.dtoToEntity(usuarioDTO)));
    }

    public TokenResponse login(String email, String senha) {
        return repository.findByEmail(email)
                .filter(usuario -> passwordEncoder.matches(senha, usuario.getSenha()))
                .map(usuario -> {
                    CompletableFuture<PerfilResponse> profileFuture = CompletableFuture.supplyAsync(() -> {
                        Long idRestaurante = null;
                        if(Objects.nonNull(usuario.getRestaurante())) {
                            idRestaurante = usuario.getRestaurante().getIdRestaurante();
                        }
                        return PerfilResponse.builder()
                                .id(usuario.getIdUsuario())
                                .nome(usuario.getNome())
                                .email(usuario.getEmail())
                                .role(usuario.getRole())
                                .idRestaurante(idRestaurante)
                                .build();
                    });

                    try {
                        PerfilResponse profile = profileFuture.join();
                        String token = authService.generateToken(usuario.getEmail(), profile);
                        log.info("Token gerado com sucesso para o usuário: {}", usuario.getEmail());

                        HttpHeaders headers = new HttpHeaders();
                        headers.add("Authorization", "Bearer " + token);

                        return TokenResponse.builder()
                                .accessToken(token)
                                .headers(headers)
                                .profile(profile)
                                .build();
                    } catch (Exception e) {
                        log.error("Erro ao gerar token para o usuário: {}", usuario.getEmail(), e);
                        throw new BusinessException("Erro ao processar login");
                    }
                })
                .orElseThrow(() -> new NotFoundException("Credenciais inválidas"));
    }

    public Boolean idValidUser(String email, String senha) {
        return repository.existsUsuarioByEmailAndSenha(email, senha);
    }

    public UsuarioDTO findByEmail(String email) {
        return repository.findByEmail(email)
                .map(usuario -> {
                    UsuarioDTO usuarioDTO = mapper.entityToBo(usuario);
                    usuarioDTO.setIdRestaurante(usuarioDTO.getRestaurante().getIdRestaurante());
                    return usuarioDTO;
                })
                .orElseThrow(() -> new NotFoundException("Usuario não encontrado"));
    }
}
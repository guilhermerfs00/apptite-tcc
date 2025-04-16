package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUsuarioRepository extends JpaRepository<Usuario, Long> {
    Boolean existsUsuarioByEmailAndSenha(String email, String senha);

    Optional<Usuario> findByEmail(String email);
}
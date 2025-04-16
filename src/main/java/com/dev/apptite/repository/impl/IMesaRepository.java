package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Mesa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IMesaRepository extends JpaRepository<Mesa, Long> {

    Optional<Mesa> findByUuid(String uuid);

    Optional<Mesa> findByNumero(int numero);

    @Query("SELECT m FROM Mesa m WHERE m.restaurante.idRestaurante = :idRestaurante")
    Page<Mesa> findByIdRestaurante(@Param("idRestaurante") Long idRestaurante, Pageable pageable);
}

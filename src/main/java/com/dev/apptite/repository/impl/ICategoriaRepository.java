package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Categoria;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ICategoriaRepository extends JpaRepository<Categoria, Long> {

    @Query("SELECT c FROM Categoria c WHERE c.restaurante.idRestaurante = :idRestaurante")
    Page<Categoria> findByIdRestaurante(@Param("idRestaurante") Long idRestaurante, Pageable pageable);

    Page<Categoria> findAll(Pageable pageable);
}

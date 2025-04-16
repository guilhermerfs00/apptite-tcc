package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Restaurante;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface IRestauranteRepository extends JpaRepository<Restaurante, Long> {

    @Query("SELECT r FROM Restaurante r WHERE LOWER(r.nome) LIKE LOWER(CONCAT('%', :search, '%'))")
    Page<Restaurante> findByNomeContainingIgnoreCase(@Param("search") String search, Pageable pageable);

    @Query("SELECT r FROM Restaurante r " +
            "JOIN r.mesas m " +
            "JOIN m.clientes c " +
            "WHERE c.idCliente = :idCliente")
    Optional<Restaurante> findByIdCliente(@Param("idCliente") Long idCliente);
}

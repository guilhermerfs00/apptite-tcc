package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Pedido;
import com.dev.apptite.domain.enums.StatusPedidoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface IPedidoRepository extends JpaRepository<Pedido, Long> {
    Page<Pedido> findAll(Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.cliente.mesa.restaurante.idRestaurante = :idRestaurante")
    Page<Pedido> findByIdRestaurante(@Param("idRestaurante") Long idRestaurante, Pageable pageable);

    @Query("SELECT p FROM Pedido p WHERE p.cliente.mesa.restaurante.idRestaurante = :idRestaurante and p.status = :status")
    Page<Pedido> findByIdRestauranteAndStatus(@Param("idRestaurante") Long idRestaurante, @Param("status") StatusPedidoEnum status, Pageable pageable);

@Query("SELECT p FROM Pedido p WHERE p.dataCriacao >= :dataInicio AND p.dataCriacao < :dataFim")
List<Pedido> findAllWithFilters(@Param("dataInicio") LocalDateTime dataInicio, @Param("dataFim") LocalDateTime dataFim);
}

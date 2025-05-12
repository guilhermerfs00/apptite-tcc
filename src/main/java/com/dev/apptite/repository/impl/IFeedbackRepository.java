package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IFeedbackRepository extends JpaRepository<Feedback, Long> {

    @Query("SELECT f FROM Feedback f WHERE f.cliente.mesa.restaurante.idRestaurante = :idRestaurante")
    List<Feedback> findByIdRestaurante(@Param("idRestaurante") Long idRestaurante);
}

package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Variacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface IVariacaoRepository extends JpaRepository<Variacao, Long> {

    @Query("SELECT v FROM Variacao v WHERE v.item.idItem = :idItem")
    Page<Variacao> findByIdItem(@Param("idItem") Long idItem, Pageable pageable);
}

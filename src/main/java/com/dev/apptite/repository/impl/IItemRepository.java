package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface IItemRepository extends JpaRepository<Item, Long> {

    Page<Item> findAll(Pageable pageable);

    @Query("SELECT i FROM Item i WHERE i.categoria.idCategoria = :idCategoria")
    Page<Item> findByIdCategoria(@Param("idCategoria") Long idCategoria, Pageable pageable);
}

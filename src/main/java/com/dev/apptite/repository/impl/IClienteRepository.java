package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IClienteRepository extends JpaRepository<Cliente,Long> {


//    boolean existsByEmail(String email);

//    boolean existsByCelular(String celular);
}

package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Cliente;
import com.dev.apptite.domain.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {

    Optional<Pagamento> findFirstByClienteOrderByDataCriacaoDesc(Cliente cliente);
}
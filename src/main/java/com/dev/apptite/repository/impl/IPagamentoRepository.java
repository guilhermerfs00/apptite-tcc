package com.dev.apptite.repository.impl;

import com.dev.apptite.domain.entity.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagamentoRepository extends JpaRepository<Pagamento, Long> {
}

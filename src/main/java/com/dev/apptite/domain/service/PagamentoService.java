package com.dev.apptite.domain.service;

import com.dev.apptite.domain.dto.PagamentoDTO;
import com.dev.apptite.domain.entity.Pagamento;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.dev.apptite.domain.mapper.PagamentoMapper;
import com.dev.apptite.repository.impl.IPagamentoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

import static com.dev.apptite.domain.enums.StatusPagamentoEnum.PAGO;
import static com.dev.apptite.domain.enums.StatusPagamentoEnum.PENDENTE;

@Service
@AllArgsConstructor
public class PagamentoService {

    private final IPagamentoRepository repository;
    private final PagamentoMapper mapper;

    public PagamentoDTO processarPagamento(PagamentoDTO pagamentoDTO) {

        // >>> Implementar validação de mesa <<<

        validarValorPago(pagamentoDTO);

        // >>> Implementar integração com api de pagamento <<<

        Pagamento pagamento = mapper.dtoToEntity(pagamentoDTO);
        pagamento.setValorPendente(pagamentoDTO.getValorTotal().subtract(pagamentoDTO.getValorPago()));

        atualizarStatus(pagamento);

        return mapper.entityToDTO(repository.save(pagamento));
    }

    private void validarValorPago(PagamentoDTO pagamentoDTO) {
        if (pagamentoDTO.getValorPago().compareTo(BigDecimal.ZERO) <= 0) {
            throw new BusinessException("O valor pago deve ser maior que zero.");
        }
        if (pagamentoDTO.getValorPago().compareTo(pagamentoDTO.getValorTotal()) > 0) {
            throw new BusinessException("O valor pago excede o valor total.");
        }
    }

    private void atualizarStatus(Pagamento pagamento) {
        if (pagamento.getValorPendente().compareTo(BigDecimal.ZERO) == 0) {
            pagamento.setStatus(PAGO);
        } else {
            pagamento.setStatus(PENDENTE);
        }
    }
}

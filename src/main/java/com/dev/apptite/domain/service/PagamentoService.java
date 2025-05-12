package com.dev.apptite.domain.service;

import com.dev.apptite.domain.dto.ClienteDTO;
import com.dev.apptite.domain.dto.PagamentoDTO;
import com.dev.apptite.domain.entity.Cliente;
import com.dev.apptite.domain.entity.Pagamento;
import com.dev.apptite.domain.enums.StatusPagamentoEnum;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.dev.apptite.domain.mapper.PagamentoMapper;
import com.dev.apptite.repository.impl.IPagamentoRepository;
import com.stripe.exception.StripeException;
import com.stripe.model.PaymentIntent;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import static com.dev.apptite.domain.enums.StatusPagamentoEnum.PAGO;
import static com.dev.apptite.domain.enums.StatusPagamentoEnum.PENDENTE;

@Service
@AllArgsConstructor
public class PagamentoService {

    private final ClienteService clienteService;
    private final IPagamentoRepository repository;
    private final PagamentoMapper mapper;
    private final StripeService stripeService;

    public PagamentoDTO processarPagamento(PagamentoDTO pagamentoDTO) {
        validarValorPago(pagamentoDTO);

        Pagamento pagamento = mapper.dtoToEntity(pagamentoDTO);

        associarCliente(pagamentoDTO, pagamento);

        try {
            criarPagamentoStripe(pagamentoDTO, pagamento);
        } catch (StripeException e) {
            throw new BusinessException("Erro ao processar pagamento no Stripe: " + e.getMessage());
        }

        atualizarStatus(pagamento);

        Pagamento saved = repository.save(pagamento);

        PagamentoDTO retorno = mapper.entityToDTO(saved);
        retorno.setValorPago(pagamentoDTO.getValorPago());
        retorno.setValorTotal(pagamentoDTO.getValorTotal());
        retorno.setStripePaymentIntentId(pagamento.getStripePaymentIntentId());
        retorno.setClientSecret(pagamentoDTO.getClientSecret());

        return retorno;
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
        if (pagamento.getStatus() == null || pagamento.getStatus() == PENDENTE) {
            pagamento.setStatus(PAGO);
        }
    }

    private void criarPagamentoStripe(PagamentoDTO pagamentoDTO, Pagamento pagamento) throws StripeException {
        var paymentMethodDto = pagamentoDTO.getMetodoDePagamento();
        var paymentMethod = stripeService.createCardPaymentMethod(paymentMethodDto);

        Map<String, Object> params = new HashMap<>();
        params.put("amount", pagamentoDTO.getValorPago().multiply(BigDecimal.valueOf(100)).longValue());
        params.put("currency", "brl");
        params.put("description", "Pagamento para o pedido #" + pagamentoDTO.getIdPagamento());
        params.put("payment_method", paymentMethod.getId());
        params.put("confirm", true);

        PaymentIntent paymentIntent = stripeService.createPaymentIntent(params);

        if (paymentIntent == null) {
            throw new BusinessException("Falha ao criar PaymentIntent");
        }

        pagamento.setStripePaymentIntentId(paymentIntent.getId());
        pagamentoDTO.setStripePaymentIntentId(paymentIntent.getId());
        pagamentoDTO.setClientSecret(paymentIntent.getClientSecret());
    }


    private void associarCliente(PagamentoDTO pagamentoDTO, Pagamento pagamento) {
        if (pagamentoDTO.getIdCliente() == null) {
            throw new BusinessException("Cliente não informado para o pagamento.");
        }

        Cliente cliente = clienteService.buscarPorIdEntity(pagamentoDTO.getIdCliente());
        pagamento.setCliente(cliente);
    }

    public StatusPagamentoEnum buscarPagamentoPorCliente(Long idCliente) {
        Cliente cliente = clienteService.buscarPorIdEntity(idCliente);

        return repository.findFirstByClienteOrderByDataCriacaoDesc(cliente)
                .map(Pagamento::getStatus)
                .orElse(PENDENTE);
    }
}

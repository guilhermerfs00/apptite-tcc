package com.dev.apptite.api.controller.pagamento;

import com.dev.apptite.api.controller.pagamento.request.PagamentoRequest;
import com.dev.apptite.api.controller.pagamento.response.PagamentoResponse;
import com.dev.apptite.domain.dto.PagamentoDTO;
import com.dev.apptite.domain.enums.StatusPagamentoEnum;
import com.dev.apptite.domain.enums.StatusPedidoEnum;
import com.dev.apptite.domain.mapper.PagamentoMapper;
import com.dev.apptite.domain.service.PagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequiredArgsConstructor
public class PagamentoController implements IPagamentoController {

    private final PagamentoService service;
    private final PagamentoMapper mapper;

    @Override
    public ResponseEntity<PagamentoResponse> create(PagamentoRequest pagamentoRequest) {
        PagamentoDTO pagamentoDTO = service.processarPagamento(mapper.requestToDto(pagamentoRequest));
        PagamentoResponse response = mapper.dtoToResponse(pagamentoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @Override
    public ResponseEntity<StatusPagamentoEnum> getStatusPagamento(Long idCliente) {
        return ResponseEntity.ok(service.buscarPagamentoPorCliente(idCliente));
    }
}

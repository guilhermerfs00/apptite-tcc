package com.dev.apptite.api.controller.pedido;

import com.dev.apptite.api.controller.pedido.request.PedidoRequest;
import com.dev.apptite.api.controller.pedido.request.PedidoUpdateRequest;
import com.dev.apptite.api.controller.pedido.response.PedidoResponse;
import com.dev.apptite.domain.dto.PedidoDTO;
import com.dev.apptite.domain.enums.StatusPedidoEnum;
import com.dev.apptite.domain.mapper.PedidoMapper;
import com.dev.apptite.domain.service.PedidoService;
import com.dev.apptite.domain.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class PedidoController implements IPedidoController {

    private final PedidoService service;
    private final PedidoMapper mapper;


    @Override
    public ResponseEntity<PedidoResponse> create(PedidoRequest request) {
        PedidoDTO pedidoDTO = service.salvar(mapper.requestToDto(request));
        PedidoResponse response = mapper.dtoToResponse(pedidoDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<PageResponse<PedidoResponse>> findByIdRestaurante(Long idRestaurante, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        PageResponse<PedidoDTO> pedidoDTO = service.findByIdRestaurante(idRestaurante, pageRequest);
        PageResponse<PedidoResponse> response = mapper.mapPageDtoToPageResponse(pedidoDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<PageResponse<PedidoResponse>> findByIdRestauranteAndStaus(Long idRestaurante, StatusPedidoEnum status,
                                                                                    int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        PageResponse<PedidoDTO> pedidoDTO = service.findByIdRestauranteAndStatus(idRestaurante, status, pageRequest);
        PageResponse<PedidoResponse> response = mapper.mapPageDtoToPageResponse(pedidoDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<PedidoResponse> updateStatusPedido(StatusPedidoEnum statusPedidoEnum, Long id) {
        PedidoDTO response = service.processarPedido(statusPedidoEnum, id);
        return ResponseEntity.status(OK).body(mapper.dtoToResponse(response));
    }

    @Override
    public ResponseEntity<PedidoResponse> findById(Long id) {
        PedidoDTO pedidoDTO = service.buscarPorId(id);
        PedidoResponse response = mapper.dtoToResponse(pedidoDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.deletarPorId(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PageResponse<PedidoResponse>> findAllPaginated(int pageNumber, int pageSize) {
        PageResponse<PedidoDTO> paginated = service.findPaginated(PageRequest.of(pageNumber, pageSize));
        PageResponse<PedidoResponse> response = mapper.mapPageDtoToPageResponse(paginated);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<List<PedidoResponse>> findAllWithFilters(String dataInicio, String dataFim) {
        List<PedidoDTO> paginated = service.findAllWithFilters(dataInicio, dataFim);
        List<PedidoResponse> response = mapper.dtoToResponse(paginated);
        return ResponseEntity.status(OK).body(response);
    }

}

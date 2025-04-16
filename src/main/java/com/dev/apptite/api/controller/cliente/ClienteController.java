package com.dev.apptite.api.controller.cliente;

import com.dev.apptite.api.controller.cliente.request.ClienteRequest;
import com.dev.apptite.api.controller.cliente.response.ClienteResponse;
import com.dev.apptite.domain.dto.ClienteDTO;
import com.dev.apptite.domain.mapper.ClienteMapper;
import com.dev.apptite.domain.service.ClienteService;
import com.dev.apptite.domain.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class ClienteController implements IClienteController {

    private final ClienteService service;
    private final ClienteMapper mapper;

    @Override
    public ResponseEntity<ClienteResponse> create(ClienteRequest request) {
        ClienteDTO clienteDTO = service.salvar(mapper.requestToDto(request));
        ClienteResponse response = mapper.dtoToResponse(clienteDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<ClienteResponse> findById(Long id) {
        ClienteDTO clienteDTO = service.buscarPorId(id);
        ClienteResponse response = mapper.dtoToResponse(clienteDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.deletarPorId(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PageResponse<ClienteResponse>> findAllPaginated(int pageNumber, int pageSize) {

        PageResponse<ClienteDTO> paginated = service.findPaginated(PageRequest.of(pageNumber, pageSize));
        PageResponse<ClienteResponse> response = mapper.mapPageDtoToPageResponse(paginated);

        return ResponseEntity.status(OK).body(response);
    }

}

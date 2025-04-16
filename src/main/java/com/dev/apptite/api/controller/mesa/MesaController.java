package com.dev.apptite.api.controller.mesa;

import com.dev.apptite.api.controller.mesa.request.MesaRequest;
import com.dev.apptite.api.controller.mesa.response.MesaResponse;
import com.dev.apptite.domain.dto.MesaDTO;
import com.dev.apptite.domain.mapper.MesaMapper;
import com.dev.apptite.domain.service.MesaService;
import com.dev.apptite.domain.utils.PageResponse;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@AllArgsConstructor
public class MesaController implements IMesaController {

    private final MesaService service;
    private final MesaMapper mapper;


    @Override
    public ResponseEntity<MesaResponse> create(MesaRequest mesaRequest) {
        MesaDTO mesaDTO = service.criarMesa(mapper.requestToDto(mesaRequest));
        MesaResponse response = mapper.dtoToResponse(mesaDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<MesaResponse> findById(Long id) {
        MesaDTO mesaDTO = service.buscarMesaPorId(id);
        MesaResponse response = mapper.dtoToResponse(mesaDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<PageResponse<MesaResponse>> findMesasByRestaurante(Long idRestaurante, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        PageResponse<MesaDTO> pageResponse = service.findByIdRestaurante(idRestaurante, pageRequest);
        PageResponse<MesaResponse> response = mapper.mapPageDtoToPageResponse(pageResponse);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.deletarPorId(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }
}

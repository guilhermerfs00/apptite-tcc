package com.dev.apptite.api.controller.restaurante;

import com.dev.apptite.api.controller.restaurante.request.RestauranteRequest;
import com.dev.apptite.api.controller.restaurante.request.RestauranteUpdateRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.mapper.RestauranteMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.service.RestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class RestauranteController implements IRestauranteController {

    private final RestauranteMapper mapper;
    private final RestauranteService service;

    @Override
    public ResponseEntity<RestauranteResponse> create(RestauranteRequest restauranteRequest) {
        RestauranteDTO response = service.salvar(mapper.requestToDto(restauranteRequest));
        return ResponseEntity.status(CREATED).body(mapper.dtoToResponse(response));
    }


    @Override
    public ResponseEntity<RestauranteResponse> update(RestauranteUpdateRequest request, Long id) {
        RestauranteDTO response = service.update(mapper.requestUpdateToDto(request), id);
        return ResponseEntity.status(OK).body(mapper.dtoToResponse(response));
    }

    @Override
    public ResponseEntity<RestauranteResponse> findById(Long id) {
        RestauranteDTO response = service.findById(id);
        return ResponseEntity.status(OK).body(mapper.dtoToResponse(response));

    }

    @Override
    public ResponseEntity<RestauranteResponse> findByIdCliente(Long idCliente) {
        RestauranteDTO response = service.findByIdCliente(idCliente);
        return ResponseEntity.status(OK).body(mapper.dtoToResponse(response));
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PageResponse<RestauranteResponse>> findAllPaginated(int pageNumber, int pageSize, String search) {
        PageResponse<RestauranteDTO> paginated = service.findPaginated(search, PageRequest.of(pageNumber, pageSize));
        PageResponse<RestauranteResponse> response = mapper.mapPageDtoToPageResponse(paginated);
        return ResponseEntity.status(OK).body(response);
    }

}

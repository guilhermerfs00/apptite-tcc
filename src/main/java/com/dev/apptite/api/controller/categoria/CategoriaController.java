package com.dev.apptite.api.controller.categoria;

import com.dev.apptite.api.controller.categoria.request.CategoriaRequest;
import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.mapper.CategoriaMapper;
import com.dev.apptite.domain.service.CategoriaService;
import com.dev.apptite.domain.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
public class CategoriaController implements ICategoriaController {

    private final CategoriaMapper mapper;
    private final CategoriaService service;

    @Override
    public ResponseEntity<CategoriaResponse> create(CategoriaRequest request) {
        CategoriaDTO categoriaDTO = service.salvar(mapper.requestToDto(request));
        CategoriaResponse response = mapper.dtoToResponse(categoriaDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<CategoriaResponse> findById(Long id) {
        CategoriaDTO categoriaDTO = service.buscarPorId(id);
        CategoriaResponse response = mapper.dtoToResponse(categoriaDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<PageResponse<CategoriaResponse>> findByIdRestaurante(Long idRestaurante, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        PageResponse<CategoriaDTO> categoriasDTO = service.findByIdRestaurante(idRestaurante, pageRequest);
        PageResponse<CategoriaResponse> response = mapper.mapPageDtoToPageResponse(categoriasDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PageResponse<CategoriaResponse>> findAllPaginated(int pageNumber, int pageSize) {
        PageResponse<CategoriaDTO> paginated = service.findPaginated(PageRequest.of(pageNumber, pageSize));
        PageResponse<CategoriaResponse> response = mapper.mapPageDtoToPageResponse(paginated);
        return ResponseEntity.status(OK).body(response);
    }
}

package com.dev.apptite.api.controller.item;

import com.dev.apptite.api.controller.item.request.ItemRequest;
import com.dev.apptite.api.controller.item.request.ItemUploadRequest;
import com.dev.apptite.api.controller.item.response.ItemResponse;
import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.mapper.ItemMapper;
import com.dev.apptite.domain.service.ItemService;
import com.dev.apptite.domain.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class ItemController implements IItemController {

    private final ItemMapper mapper;
    private final ItemService service;

    @Override
    public ResponseEntity<ItemResponse> create(ItemRequest request) {
        ItemDTO itemDTO = service.salvar(mapper.requestToDto(request));
        ItemResponse response = mapper.dtoToResponse(itemDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<ItemResponse> findById(Long id) {
        ItemDTO itemDTO = service.buscarPorId(id);
        ItemResponse response = mapper.dtoToResponse(itemDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.delete(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PageResponse<ItemResponse>> findAllPaginated(int pageNumber, int pageSize) {
        PageResponse<ItemDTO> paginated = service.findPaginated(PageRequest.of(pageNumber, pageSize));
        PageResponse<ItemResponse> response = mapper.mapPageDtoToPageResponse(paginated);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<PageResponse<ItemResponse>> findByIdCategoria(Long idCategoria, int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);
        PageResponse<ItemDTO> pageResponse = service.findByIdCategoria(idCategoria, pageRequest);
        PageResponse<ItemResponse> response = mapper.mapPageDtoToPageResponse(pageResponse);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<ItemResponse> uploadItem(ItemUploadRequest request) {
        ItemDTO salvo = service.uploadItem(
                request.getNome(),
                request.getDescricao(),
                request.getPreco(),
                request.getIdCategoria(),
                request.getImagem()
        );

        ItemResponse response = mapper.dtoToResponse(salvo);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<String> getItemImage(Long id) {
        String base64Image = service.getItemImage(id);
        return ResponseEntity.status(OK).body(base64Image);
    }

}


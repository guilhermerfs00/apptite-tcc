package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.categoria.request.CategoriaRequest;
import com.dev.apptite.api.controller.categoria.response.CategoriaResponse;
import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.utils.PageResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoriaMapper {

    CategoriaDTO entityToDTO(Categoria entity);

    Categoria dtoToEntity(CategoriaDTO dto);

    CategoriaResponse dtoToResponse(CategoriaDTO dto);

    CategoriaDTO requestToDto(CategoriaRequest request);

    List<CategoriaDTO> entityToDTO(List<Categoria> entity);

    List<CategoriaResponse> dtoToResponse(List<CategoriaDTO> dto);

    PageResponse<CategoriaDTO> mapPageEntityToPageDto(PageResponse<Categoria> page);

    PageResponse<CategoriaResponse> mapPageDtoToPageResponse(PageResponse<CategoriaDTO> page);
}

package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.restaurante.request.RestauranteRequest;
import com.dev.apptite.api.controller.restaurante.request.RestauranteUpdateRequest;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.domain.utils.PageResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RestauranteMapper {

    RestauranteDTO entityToDTO(Restaurante entity);
    Restaurante dtoToEntity(RestauranteDTO dto);
    RestauranteResponse dtoToResponse(RestauranteDTO dto);
    RestauranteDTO requestToDto(RestauranteRequest request);
    RestauranteDTO requestUpdateToDto(RestauranteUpdateRequest request);

    List<RestauranteDTO> entityToDTO(List<Restaurante> entity);
    List<RestauranteResponse> dtoToResponse(List<RestauranteDTO> dto);

    PageResponse<RestauranteDTO> mapPageEntityToPageDto(PageResponse<Restaurante> page);
    PageResponse<RestauranteResponse> mapPageDtoToPageResponse(PageResponse<RestauranteDTO> page);

}

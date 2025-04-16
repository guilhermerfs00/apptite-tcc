package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.mesa.request.MesaRequest;
import com.dev.apptite.api.controller.mesa.response.MesaResponse;
import com.dev.apptite.domain.dto.MesaDTO;
import com.dev.apptite.domain.entity.Mesa;
import com.dev.apptite.domain.utils.PageResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MesaMapper {

    MesaDTO entityToDTO(Mesa entity);

    Mesa dtoToEntity(MesaDTO dto);

    MesaDTO requestToDto(MesaRequest request);

    MesaResponse dtoToResponse(MesaDTO dto);
    PageResponse<MesaDTO> mapPageEntityToPageDto(PageResponse<Mesa> page);
    PageResponse<MesaResponse> mapPageDtoToPageResponse(PageResponse<MesaDTO> page);

}

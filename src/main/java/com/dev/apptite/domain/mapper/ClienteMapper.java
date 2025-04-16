package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.cliente.request.ClienteRequest;
import com.dev.apptite.api.controller.cliente.response.ClienteResponse;
import com.dev.apptite.domain.dto.ClienteDTO;
import com.dev.apptite.domain.entity.Cliente;
import com.dev.apptite.domain.utils.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {

    @Mapping(target = "pedidos", ignore = true)
    ClienteDTO entityToDTO(Cliente entity);

    Cliente dtoToEntity(ClienteDTO dto);

    ClienteResponse dtoToResponse(ClienteDTO dto);

    ClienteDTO requestToDto(ClienteRequest request);

    List<ClienteDTO> entityToDTO(List<Cliente> entity);

    List<ClienteResponse> dtoToResponse(List<ClienteDTO> dto);

    PageResponse<ClienteDTO> mapPageEntityToPageDto(PageResponse<Cliente> page);

    PageResponse<ClienteResponse> mapPageDtoToPageResponse(PageResponse<ClienteDTO> page);
}

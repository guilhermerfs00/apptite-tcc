package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.pedido.request.PedidoRequest;
import com.dev.apptite.api.controller.pedido.request.PedidoUpdateRequest;
import com.dev.apptite.api.controller.pedido.response.PedidoResponse;
import com.dev.apptite.domain.dto.PedidoDTO;
import com.dev.apptite.domain.entity.Pedido;
import com.dev.apptite.domain.utils.PageResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(uses = CategoriaMapper.class, componentModel = "spring")
public interface PedidoMapper {

    @Mapping(target = "cliente.pedidos", ignore = true)
    PedidoDTO entityToDTO(Pedido entity);

    List<PedidoDTO> entityToDTO(List<Pedido> entity);

    Pedido dtoToEntity(PedidoDTO dto);

    PedidoDTO requestToDto(PedidoRequest request);

    PedidoDTO requestToDto(PedidoUpdateRequest request);

    List<PedidoResponse> dtoToResponse(List<PedidoDTO> dto);

    PedidoResponse dtoToResponse(PedidoDTO dto);

    PageResponse<PedidoDTO> mapPageEntityToPageDto(PageResponse<Pedido> page);

    PageResponse<PedidoResponse> mapPageDtoToPageResponse(PageResponse<PedidoDTO> page);

}

package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.item.request.ItemRequest;
import com.dev.apptite.api.controller.item.response.ItemResponse;
import com.dev.apptite.api.controller.restaurante.response.RestauranteResponse;
import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Item;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.domain.utils.PageResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = CategoriaMapper.class, componentModel = "spring")
public interface ItemMapper {

    ItemDTO entityToDTO(Item entity);

    List<ItemDTO> entityToDTO(List<Item> entity);

    Item dtoToEntity(ItemDTO dto);

    ItemDTO requestToDto(ItemRequest request);

    List<ItemResponse> dtoToResponse(List<ItemDTO> dto);

    ItemResponse dtoToResponse(ItemDTO dto);

    PageResponse<ItemDTO> mapPageEntityToPageDto(PageResponse<Item> page);
    PageResponse<ItemResponse> mapPageDtoToPageResponse(PageResponse<ItemDTO> page);

}

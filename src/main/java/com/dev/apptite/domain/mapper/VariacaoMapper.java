package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.variacao.request.VariacaoRequest;
import com.dev.apptite.api.controller.variacao.response.VariacaoResponse;
import com.dev.apptite.domain.dto.VariacaoDTO;
import com.dev.apptite.domain.entity.Variacao;
import com.dev.apptite.domain.utils.PageResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(uses = CategoriaMapper.class, componentModel = "spring")
public interface VariacaoMapper {

    VariacaoDTO entityToDTO(Variacao entity);

    List<VariacaoDTO> entityToDTO(List<Variacao> entity);

    Variacao dtoToEntity(VariacaoDTO dto);

    VariacaoDTO requestToDto(VariacaoRequest request);

    List<VariacaoResponse> dtoToResponse(List<VariacaoDTO> dto);

    VariacaoResponse dtoToResponse(VariacaoDTO dto);

    PageResponse<VariacaoDTO> mapPageEntityToPageDto(PageResponse<Variacao> page);
    PageResponse<VariacaoResponse> mapPageDtoToPageResponse(PageResponse<VariacaoDTO> page);

}

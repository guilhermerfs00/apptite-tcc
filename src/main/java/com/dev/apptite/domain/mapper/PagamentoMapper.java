package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.pagamento.request.PagamentoRequest;
import com.dev.apptite.api.controller.pagamento.response.PagamentoResponse;
import com.dev.apptite.domain.dto.PagamentoDTO;
import com.dev.apptite.domain.entity.Pagamento;
import com.dev.apptite.domain.utils.PageResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagamentoMapper {

    PagamentoDTO entityToDTO(Pagamento entity);

    Pagamento dtoToEntity(PagamentoDTO dto);

    PagamentoResponse dtoToResponse(PagamentoDTO dto);

    PagamentoDTO requestToDto(PagamentoRequest request);

    List<PagamentoDTO> entityToDTO(List<Pagamento> entity);

    List<PagamentoResponse> dtoToResponse(List<PagamentoDTO> dto);

    PageResponse<PagamentoDTO> mapPageEntityToPageDto(PageResponse<Pagamento> page);
    PageResponse<PagamentoResponse> mapPageDtoToPageResponse(PageResponse<PagamentoDTO> page);
}

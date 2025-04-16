package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.feedback.request.FeedbackRequest;
import com.dev.apptite.api.controller.feedback.response.FeedbackResponse;
import com.dev.apptite.domain.dto.FeedbackDTO;
import com.dev.apptite.domain.entity.Feedback;
import com.dev.apptite.domain.utils.PageResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface FeedbackMapper {

    FeedbackDTO entityToDTO(Feedback entity);

    Feedback dtoToEntity(FeedbackDTO dto);

    FeedbackResponse dtoToResponse(FeedbackDTO dto);

    FeedbackDTO requestToDto(FeedbackRequest request);

    List<FeedbackDTO> entityToDTO(List<Feedback> entity);

    List<FeedbackResponse> dtoToResponse(List<FeedbackDTO> dto);

    PageResponse<FeedbackDTO> mapPageEntityToPageDto(PageResponse<Feedback> page);

    PageResponse<FeedbackResponse> mapPageDtoToPageResponse(PageResponse<FeedbackDTO> page);
}
package com.dev.apptite.api.controller.feedback;

import com.dev.apptite.api.controller.feedback.request.FeedbackRequest;
import com.dev.apptite.api.controller.feedback.request.FeedbackUpdateRequest;
import com.dev.apptite.api.controller.feedback.response.FeedbackResponse;
import com.dev.apptite.domain.dto.FeedbackDTO;
import com.dev.apptite.domain.dto.MensagemDTO;
import com.dev.apptite.domain.mapper.FeedbackMapper;
import com.dev.apptite.domain.service.FeedbackService;
import com.dev.apptite.domain.utils.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequiredArgsConstructor
public class FeedbackController implements IFeedbackController {

    private final FeedbackService service;
    private final FeedbackMapper mapper;


    @Override
    public ResponseEntity<FeedbackResponse> create(FeedbackRequest feedbackRequest) {
        FeedbackDTO feedbackDTO = service.criarFeedback(mapper.requestToDto(feedbackRequest));
        FeedbackResponse response = mapper.dtoToResponse(feedbackDTO);
        return ResponseEntity.status(CREATED).body(response);
    }

    @Override
    public ResponseEntity<FeedbackResponse> findById(Long id) {
        FeedbackDTO feedbackDTO = service.buscarPorId(id);
        FeedbackResponse response = mapper.dtoToResponse(feedbackDTO);
        return ResponseEntity.status(OK).body(response);
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        service.deletarPorId(id);
        return ResponseEntity.status(NO_CONTENT).build();
    }

    @Override
    public ResponseEntity<PageResponse<FeedbackResponse>> findAllPaginated(int pageNumber, int pageSize) {
        PageResponse<FeedbackDTO> paginated = service.findPaginated(PageRequest.of(pageNumber, pageSize));
        PageResponse<FeedbackResponse> response = mapper.mapPageDtoToPageResponse(paginated);

        return ResponseEntity.status(OK).body(response);
    }

    public ResponseEntity<FeedbackResponse> responderFeedback(Long id, FeedbackUpdateRequest request) {
        FeedbackDTO feedbackDTO = service.responderFeedback(id, request.getResposta());
        FeedbackResponse response = mapper.dtoToResponse(feedbackDTO);
        return ResponseEntity.status(CREATED).body(response);
    }
}


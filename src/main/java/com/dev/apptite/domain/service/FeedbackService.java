package com.dev.apptite.domain.service;

import com.dev.apptite.domain.dto.ClienteDTO;
import com.dev.apptite.domain.dto.FeedbackDTO;
import com.dev.apptite.domain.entity.Feedback;
import com.dev.apptite.domain.entity.Mensagem;
import com.dev.apptite.domain.entity.Pedido;
import com.dev.apptite.domain.enums.StatusPedidoEnum;
import com.dev.apptite.domain.enums.TipoMensagemEnum;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.FeedbackMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.impl.IFeedbackRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackMapper mapper;
    private final IFeedbackRepository repository;
    private final ClienteService clienteService;
    private final PageResponseMapper pageResponseMapper;

    @Transactional
    public FeedbackDTO criarFeedback(FeedbackDTO feedbackDTO) {
        associarCliente(feedbackDTO);
        Feedback feedback = mapper.dtoToEntity(feedbackDTO);
        Mensagem mensagem = criarMensagem(feedbackDTO.getConteudo(), TipoMensagemEnum.PERGUNTA, feedback);
        feedback.setMensagens(Collections.singletonList(mensagem));
        feedback = repository.save(feedback);
        return mapper.entityToDTO(feedback);
    }

    @Transactional
    public FeedbackDTO responderFeedback(Long id, String resposta) {
        FeedbackDTO feedbackDTO = buscarPorId(id);
        Feedback feedback = mapper.dtoToEntity(feedbackDTO);
        Mensagem mensagem = criarMensagem(resposta, TipoMensagemEnum.RESPOSTA, feedback);
        List<Mensagem> mensagens = new ArrayList<>(Optional.ofNullable(feedback.getMensagens()).orElseGet(ArrayList::new));
        mensagens.add(mensagem);
        feedback.setMensagens(mensagens);
        return mapper.entityToDTO(repository.save(feedback));
    }

    public FeedbackDTO buscarPorId(Long id) {
        Feedback feedback = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("feedback.not-found.error", id));
        return mapper.entityToDTO(feedback);
    }

    @Transactional
    public void deletarPorId(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public PageResponse<FeedbackDTO> findPaginated(PageRequest pageable) {
        Page<Feedback> itemPage = repository.findAll(pageable);
        PageResponse<Feedback> page = pageResponseMapper.pageToPageResponse(itemPage);
        return mapper.mapPageEntityToPageDto(page);
    }

    private void associarCliente(FeedbackDTO feedbackDTO) {
        ClienteDTO cliente = clienteService.buscarPorId(feedbackDTO.getIdCliente());
        feedbackDTO.setCliente(cliente);
    }

    private Mensagem criarMensagem(String conteudo, TipoMensagemEnum tipo, Feedback feedback) {
        return Mensagem.builder()
                .tipoMensagem(tipo)
                .conteudo(conteudo)
                .feedback(feedback)
                .build();
    }
}
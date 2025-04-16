package com.dev.apptite.domain.service;

import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.dto.VariacaoDTO;
import com.dev.apptite.domain.entity.Variacao;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.VariacaoMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.impl.IVariacaoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class VariacaoService {

    private final VariacaoMapper mapper;
    private final IVariacaoRepository repository;
    private final PageResponseMapper pageResponseMapper;
    private final ItemService itemService;

    public VariacaoDTO salvar(VariacaoDTO variacaoDTO) {

        associarVariacao(variacaoDTO);
        Variacao variacao = mapper.dtoToEntity(variacaoDTO);
        return mapper.entityToDTO(repository.save(variacao));
    }

    public VariacaoDTO buscarPorId(Long id) {
        Variacao variacao = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("variation.not-found.error", id));
        return mapper.entityToDTO(variacao);
    }

    public void delete(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public PageResponse<VariacaoDTO> findPaginated(PageRequest pageable) {
        Page<Variacao> variacaoPage = repository.findAll(pageable);
        PageResponse<Variacao> page = pageResponseMapper.pageToPageResponse(variacaoPage);
        return mapper.mapPageEntityToPageDto(page);
    }

    private void associarVariacao(VariacaoDTO variacaoDTO) {
        if (variacaoDTO.getIdItem() != null) {
            ItemDTO itemDTO = itemService.buscarPorId(variacaoDTO.getIdItem());
            variacaoDTO.setItem(itemDTO);
            variacaoDTO.setIdItem(itemDTO.getIdItem());
        }
    }

    public PageResponse<VariacaoDTO> findByIdItem(Long idItem, PageRequest pageRequest) {
        Page<Variacao> categoriaPage = repository.findByIdItem(idItem, pageRequest);
        PageResponse<Variacao> page = pageResponseMapper.pageToPageResponse(categoriaPage);
        return mapper.mapPageEntityToPageDto(page);
    }
}

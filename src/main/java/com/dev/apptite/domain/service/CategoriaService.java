package com.dev.apptite.domain.service;

import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Categoria;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.CategoriaMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.impl.ICategoriaRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CategoriaService {

    private final CategoriaMapper mapper;
    private final ICategoriaRepository repository;
    private final PageResponseMapper pageResponseMapper;
    private final RestauranteService restauranteService;

    public CategoriaDTO salvar(CategoriaDTO categoriaDTO) {
        associarRestaurnate(categoriaDTO);
        Categoria categoria = mapper.dtoToEntity(categoriaDTO);
        return mapper.entityToDTO(repository.save(categoria));
    }

    public CategoriaDTO buscarPorId(Long id) {
        Categoria categoria = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("category.not-found.error", id));
        return mapper.entityToDTO(categoria);
    }

    public void delete(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public PageResponse<CategoriaDTO> findByIdRestaurante(Long idRestaurante, PageRequest pageRequest) {
        Page<Categoria> categoriaPage = repository.findByIdRestaurante(idRestaurante, pageRequest);
        PageResponse<Categoria> page = pageResponseMapper.pageToPageResponse(categoriaPage);
        return mapper.mapPageEntityToPageDto(page);
    }


    public PageResponse<CategoriaDTO> findPaginated(PageRequest pageable) {
        Page<Categoria> categoriaPage = repository.findAll(pageable);
        PageResponse<Categoria> page = pageResponseMapper.pageToPageResponse(categoriaPage);
        return mapper.mapPageEntityToPageDto(page);
    }

    private void associarRestaurnate(CategoriaDTO categoriaDTO) {
        if (categoriaDTO.getIdRestaurante() != null) {
            RestauranteDTO restauranteDTO = restauranteService.findById(categoriaDTO.getIdRestaurante());
            categoriaDTO.setRestaurante(restauranteDTO);
            categoriaDTO.setIdRestaurante(restauranteDTO.getIdRestaurante());
        }
    }
}

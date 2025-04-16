package com.dev.apptite.domain.service;

import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Restaurante;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.RestauranteMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.impl.IRestauranteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RestauranteService {

    private final RestauranteMapper mapper;
    private final IRestauranteRepository repository;
    private final PageResponseMapper pageResponseMapper;

    public RestauranteDTO salvar(RestauranteDTO restauranteDTO) {
        Restaurante restaurante = mapper.dtoToEntity(restauranteDTO);
        return mapper.entityToDTO(repository.save(restaurante));
    }

    public RestauranteDTO update(RestauranteDTO restauranteNovoDTO, Long id) {
        Restaurante restaurante = mapper.dtoToEntity(restauranteNovoDTO);
        restaurante.setIdRestaurante(id);
        return mapper.entityToDTO(repository.save(restaurante));
    }

    public RestauranteDTO findById(Long id) {
        Restaurante restaurante = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("restaurant.not-found.error", id));
        return mapper.entityToDTO(restaurante);
    }


    public RestauranteDTO findByIdCliente(Long idCliente) {
        Restaurante restaurante = repository.findByIdCliente(idCliente)
                .orElseThrow(() -> new NotFoundException("restaurant.mesa.not-found.error", idCliente));
        return mapper.entityToDTO(restaurante);
    }

    public void delete(Long id) {
        findById(id);
        repository.deleteById(id);
    }

    public PageResponse<RestauranteDTO> findPaginated(String search, Pageable pageable) {
        Page<Restaurante> restaurantePage;

        if (search == null || search.trim().isEmpty()) {
            restaurantePage = repository.findAll(pageable);
        } else {
            restaurantePage = repository.findByNomeContainingIgnoreCase(search, pageable);
        }

        PageResponse<Restaurante> page = pageResponseMapper.pageToPageResponse(restaurantePage);
        return mapper.mapPageEntityToPageDto(page);
    }

}

package com.dev.apptite.domain.service;

import com.dev.apptite.domain.dto.ClienteDTO;
import com.dev.apptite.domain.dto.MesaDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Cliente;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.ClienteMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.impl.IClienteRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClienteService {

    private final ClienteMapper mapper;
    private final IClienteRepository repository;
    private final PageResponseMapper pageResponseMapper;
    private final MesaService mesaService;

    public ClienteDTO salvar(ClienteDTO clienteDTO) {
        associarMesa(clienteDTO);
        Cliente cliente = mapper.dtoToEntity(clienteDTO);
        return mapper.entityToDTO(repository.save(cliente));
    }

    public ClienteDTO buscarPorId(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("client.not-found.error", id));
        return mapper.entityToDTO(cliente);
    }

    public void deletarPorId(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public PageResponse<ClienteDTO> findPaginated(PageRequest pageable) {
        Page<Cliente> itemPage = repository.findAll(pageable);
        PageResponse<Cliente> page = pageResponseMapper.pageToPageResponse(itemPage);
        return mapper.mapPageEntityToPageDto(page);
    }

    private void associarMesa(ClienteDTO clienteDTO) {
        if (clienteDTO.getIdMesa() != null) {
            MesaDTO mesaDTO = mesaService.buscarMesaPorId(clienteDTO.getIdMesa());
            clienteDTO.setMesa(mesaDTO);
            clienteDTO.setIdMesa(clienteDTO.getIdCliente());
        } else {
            throw new NotFoundException("restaurant.not-found.error");
        }
    }
}

package com.dev.apptite.domain.service;

import com.dev.apptite.domain.dto.ClienteDTO;
import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.dto.PedidoDTO;
import com.dev.apptite.domain.dto.VariacaoDTO;
import com.dev.apptite.domain.entity.Pedido;
import com.dev.apptite.domain.enums.StatusPedidoEnum;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.PedidoMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.impl.IPedidoRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class PedidoService {

    private final PedidoMapper mapper;
    private final IPedidoRepository repository;
    private final PageResponseMapper pageResponseMapper;
    private final VariacaoService variacaoService;
    private final StatusService statusService;
    private final ClienteService clienteService;
    private final ItemService itemService;

    public PedidoDTO salvar(PedidoDTO pedidoDTO) {
        validarPedido(pedidoDTO);
        associarItem(pedidoDTO);
        associarVariacao(pedidoDTO);
        associarCliente(pedidoDTO);
        processarPedido(StatusPedidoEnum.PEDIDO_REALIZADO, pedidoDTO);
        return pedidoDTO;
    }

    public PageResponse<PedidoDTO> findByIdRestaurante(Long idRestaurante, PageRequest pageRequest) {
        Page<Pedido> pageResponse = repository.findByIdRestaurante(idRestaurante, pageRequest);
        return mapPageToDTO(pageResponse);
    }

    public PageResponse<PedidoDTO> findByIdRestauranteAndStatus(Long idRestaurante, StatusPedidoEnum status, PageRequest pageRequest) {
        Page<Pedido> pageResponse = repository.findByIdRestauranteAndStatus(idRestaurante, status, pageRequest);
        return mapPageToDTO(pageResponse);
    }

    public List<PedidoDTO> findAllWithFilters(String dataInicio, String dataFim) {
        if (dataInicio == null || dataFim == null) {
            List<Pedido> response = repository.findAll();
            return mapper.entityToDTO(response);
        } else {
            LocalDateTime inicio = LocalDate.parse(dataInicio).atStartOfDay();
            LocalDateTime fim = LocalDate.parse(dataFim).plusDays(1).atStartOfDay();
            List<Pedido> response = repository.findAllWithFilters(inicio, fim);
            return mapper.entityToDTO(response);
        }
    }

    public PedidoDTO buscarPorId(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado com o ID: " + id));
        return mapper.entityToDTO(pedido);
    }

    public void deletarPorId(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public PedidoDTO processarPedido(StatusPedidoEnum status, Long idPedido) {
        PedidoDTO pedidoDTO = buscarPedidoDTO(idPedido);
        return processarPedido(status, pedidoDTO);
    }

    public PedidoDTO processarPedido(StatusPedidoEnum status, PedidoDTO pedidoDTO) {
        statusService.processarStatus(pedidoDTO, status);
        Pedido pedido = repository.save(mapper.dtoToEntity(pedidoDTO));
        return mapper.entityToDTO(pedido);
    }

    public PageResponse<PedidoDTO> findPaginated(PageRequest pageable) {
        Page<Pedido> page = repository.findAll(pageable);
        return mapPageToDTO(page);
    }

    private void associarVariacao(PedidoDTO pedidoDTO) {
        if (pedidoDTO.getIdsVariacao() != null && !pedidoDTO.getIdsVariacao().isEmpty()) {
            List<VariacaoDTO> variacoesDTO = pedidoDTO.getIdsVariacao().stream()
                    .map(variacaoService::buscarPorId)
                    .toList();
            pedidoDTO.setVariacoes(variacoesDTO);
            pedidoDTO.setIdsVariacao(variacoesDTO.stream().map(VariacaoDTO::getIdVariacao).toList());
        }
    }

    private void associarItem(PedidoDTO pedidoDTO) {
        if (pedidoDTO.getIdItem() != null) {
            ItemDTO item = itemService.buscarPorId(pedidoDTO.getIdItem());
            pedidoDTO.setItens(List.of(item));
        }
    }

    private void associarCliente(PedidoDTO pedidoDTO) {
        if (pedidoDTO.getIdCliente() != null) {
            ClienteDTO cliente = clienteService.buscarPorId(pedidoDTO.getIdCliente());
            pedidoDTO.setCliente(cliente);
        }
    }

    private PedidoDTO buscarPedidoDTO(Long idPedido) {
        return repository.findById(idPedido)
                .map(mapper::entityToDTO)
                .orElseThrow(() -> new NotFoundException("Pedido não encontrado com o ID: " + idPedido));
    }

    private PageResponse<PedidoDTO> mapPageToDTO(Page<Pedido> page) {
        PageResponse<Pedido> pageResponse = pageResponseMapper.pageToPageResponse(page);
        return mapper.mapPageEntityToPageDto(pageResponse);
    }

    private void validarPedido(PedidoDTO pedidoDTO) {
        if (pedidoDTO == null) {
            throw new IllegalArgumentException("O pedido não pode ser nulo.");
        }
    }
}
package com.dev.apptite.domain.service;

import com.dev.apptite.domain.dto.CategoriaDTO;
import com.dev.apptite.domain.dto.ItemDTO;
import com.dev.apptite.domain.entity.Item;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.ItemMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.impl.IItemRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

@Service
@AllArgsConstructor
public class ItemService {

    private final ItemMapper mapper;
    private final IItemRepository repository;
    private final PageResponseMapper pageResponseMapper;
    private final CategoriaService categoriaService;

    public ItemDTO salvar(ItemDTO itemDTO) {

        associarCategoria(itemDTO);
        Item item = mapper.dtoToEntity(itemDTO);
        return mapper.entityToDTO(repository.save(item));
    }

    public ItemDTO buscarPorId(Long id) {
        Item item = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("item.not-found.error", id));
        return mapper.entityToDTO(item);
    }

    public Item buscarEntidadePorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException("item.not-found.error", id));
    }


    public void delete(Long id) {
        buscarPorId(id);
        repository.deleteById(id);
    }

    public PageResponse<ItemDTO> findPaginated(PageRequest pageable) {

        Page<Item> itemPage = repository.findAll(pageable);
        PageResponse<Item> page = pageResponseMapper.pageToPageResponse(itemPage);
        return mapper.mapPageEntityToPageDto(page);
    }

    private void associarCategoria(ItemDTO itemDTO) {
        if (itemDTO.getIdCategoria() != null) {
            CategoriaDTO categoriaDTO = categoriaService.buscarPorId(itemDTO.getIdCategoria());
            itemDTO.setCategoria(categoriaDTO);
            itemDTO.setIdCategoria(categoriaDTO.getIdCategoria());
        }
    }

    public PageResponse<ItemDTO> findByIdCategoria(Long idCategoria, PageRequest pageRequest) {
        Page<Item> categoriaPage = repository.findByIdCategoria(idCategoria, pageRequest);
        PageResponse<Item> page = pageResponseMapper.pageToPageResponse(categoriaPage);
        return mapper.mapPageEntityToPageDto(page);
    }

    public ItemDTO uploadItem(String nome, String descricao, Double preco, Long idCategoria, MultipartFile imagem) {

        ItemDTO itemDTO = new ItemDTO();
        itemDTO.setNome(nome);
        itemDTO.setDescricao(descricao);
        itemDTO.setPreco(preco);
        itemDTO.setIdCategoria(idCategoria);

        ItemDTO salvo = this.salvar(itemDTO);

        if (imagem != null && !imagem.isEmpty()) {
            try {
                String nomeArquivo = "item_" + salvo.getIdItem() + ".jpg";
                Path pasta = Paths.get("src/main/resources/static/assets/img/");
                Files.createDirectories(pasta);
                Files.write(pasta.resolve(nomeArquivo), imagem.getBytes());
            } catch (IOException e) {
                throw new BusinessException("imagem.upload.error", e);
            }
        }
        return salvo;
    }

    public String getItemImage(Long id) {
        Item item = buscarEntidadePorId(id);
        String nomeArquivo = "item_" + item.getIdItem() + ".jpg";
        Path caminhoArquivo = Paths.get("src/main/resources/static/assets/img/", nomeArquivo);

        if (!Files.exists(caminhoArquivo)) {
            throw new NotFoundException("imagem.not-found.error", id);
        }

        try {
            byte[] imagemBytes = Files.readAllBytes(caminhoArquivo);
            return Base64.getEncoder().encodeToString(imagemBytes);
        } catch (IOException e) {
            throw new BusinessException("imagem.load.error", e);
        }
    }

}

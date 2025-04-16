package com.dev.apptite.api.controller.item.request;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ItemUploadRequest {
    private String nome;
    private String descricao;
    private Double preco;
    private Long idCategoria;
    private MultipartFile imagem;
}

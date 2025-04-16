package com.dev.apptite.domain.service;

import com.dev.apptite.domain.dto.MesaDTO;
import com.dev.apptite.domain.dto.RestauranteDTO;
import com.dev.apptite.domain.entity.Mesa;
import com.dev.apptite.domain.exceptions.BusinessException;
import com.dev.apptite.domain.exceptions.NotFoundException;
import com.dev.apptite.domain.mapper.MesaMapper;
import com.dev.apptite.domain.utils.PageResponse;
import com.dev.apptite.domain.utils.PageResponseMapper;
import com.dev.apptite.repository.impl.IMesaRepository;
import lombok.AllArgsConstructor;
import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MesaService {

    private final MesaMapper mapper;
    private final PageResponseMapper pageResponseMapper;
    private final IMesaRepository repository;
    private final RestauranteService restauranteService;

    public MesaDTO criarMesa(MesaDTO mesaDTO) {
        associarRestaurante(mesaDTO);
        Mesa mesa = mapper.dtoToEntity(mesaDTO);
        mesa.setUuid(UUID.randomUUID().toString());
        return mapper.entityToDTO(repository.save(mesa));
    }

    public byte[] gerarQrCode(String uuid) {
        Optional<Mesa> mesaOpt = repository.findByUuid(uuid);
        if (mesaOpt.isEmpty()) {
            throw new NotFoundException("table.not-found.error", uuid);
        }

        String qrData = "https://colocarositeaqui.com/mesa/" + uuid;
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        QRCode.from(qrData)
                .withSize(250, 250)
                .to(ImageType.PNG)
                .writeTo(stream);

        return stream.toByteArray();
    }

    public MesaDTO buscarMesaPorId(Long id) {
        Mesa mesa = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("table.not-found.error", id));
        MesaDTO mesaDTO = mapper.entityToDTO(mesa);
        if (mesa.getRestaurante() != null) {
            mesaDTO.setIdRestaurante(mesa.getRestaurante().getIdRestaurante());
        }
        return mesaDTO;
    }

    public void deletarPorId(Long id) {
        buscarMesaPorId(id);
        repository.deleteById(id);
    }

    private void associarRestaurante(MesaDTO mesaDTO) {
        if (mesaDTO.getIdRestaurante() != null) {
            RestauranteDTO restauranteDTO = restauranteService.findById(mesaDTO.getIdRestaurante());
            mesaDTO.setRestaurante(restauranteDTO);
            mesaDTO.setIdRestaurante(mesaDTO.getIdRestaurante());
        } else {
            throw new NotFoundException("restaurant.not-found.error");
        }
    }

    public PageResponse<MesaDTO> findByIdRestaurante(Long idRestaurante, PageRequest pageRequest) {
        Page<Mesa> categoriaPage = repository.findByIdRestaurante(idRestaurante, pageRequest);
        PageResponse<Mesa> page = pageResponseMapper.pageToPageResponse(categoriaPage);
        return mapper.mapPageEntityToPageDto(page);
    }
}

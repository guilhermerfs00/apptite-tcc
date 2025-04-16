package com.dev.apptite.domain.mapper;

import com.dev.apptite.api.controller.usuario.request.UserRequest;
import com.dev.apptite.api.controller.usuario.response.UserResponse;
import com.dev.apptite.domain.dto.UsuarioDTO;
import com.dev.apptite.domain.entity.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {

    UsuarioDTO entityToBo(Usuario entity);

    Usuario dtoToEntity(UsuarioDTO dto);

    UsuarioDTO requestToDto(UserRequest request);

    UserResponse dtoToResponse(UsuarioDTO bo);

}

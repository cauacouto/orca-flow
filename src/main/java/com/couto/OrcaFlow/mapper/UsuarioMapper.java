package com.couto.OrcaFlow.mapper;

import com.couto.OrcaFlow.domin.Usuario;
import com.couto.OrcaFlow.dto.UsuarioDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {


UsuarioDto toResponse(Usuario usuario);
}

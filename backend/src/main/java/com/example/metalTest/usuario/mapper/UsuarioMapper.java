package com.example.metalTest.usuario.mapper;

import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuario.controller.request.UsuarioRequest;
import com.example.metalTest.usuario.domain.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TipoRepository.class)
public interface UsuarioMapper {

    Usuario usuarioRequestToUsuario(UsuarioRequest usuarioRequest);

}

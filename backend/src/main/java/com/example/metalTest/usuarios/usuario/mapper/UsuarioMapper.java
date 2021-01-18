package com.example.metalTest.usuarios.usuario.mapper;

import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.usuario.controller.request.UsuarioRequest;
import com.example.metalTest.usuarios.usuario.domain.Usuario;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = TipoRepository.class)
public interface UsuarioMapper {

    Usuario usuarioRequestToUsuario(UsuarioRequest usuarioRequest);

}

package com.example.metalTest.usuario.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuario.controller.request.UsuarioRequest;
import com.example.metalTest.usuario.domain.Usuario;
import com.example.metalTest.usuario.mapper.UsuarioMapper;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import com.example.metalTest.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    TipoRepository tipoRepository;

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getById(Integer id) throws ValidateFieldException {
        Optional<Usuario> op = usuarioRepository.findById(id);
        if (op.isPresent()) {
            return op.get();
        } else {
            throw new ValidateFieldException("El usuario quE desea acceder no existe", "id", id.toString());
        }

    }

    @Override
    public Usuario create(UsuarioRequest usuario) throws ValidateFieldException {
        Usuario usrActual = usuarioMapper.usuarioRequestToUsuario(usuario);
        usrActual.setCargo(tipoRepository.findById(usuario.getCargo()).get());
        usuarioRepository.save(usrActual);
        return null;
    }

    @Override
    public Usuario update(Integer id, UsuarioRequest usuario) throws ValidateFieldException {
        if (usuario.getEstado() != Estado.ACTIVO.getValue() && usuario.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(usuario.getEstado()));
        }
        Usuario usrActual = usuarioMapper.usuarioRequestToUsuario(usuario);
        usrActual.setCargo(tipoRepository.findById(usuario.getCargo()).get());
        usrActual.setId(id);
        usuarioRepository.save(usrActual);
        return null;
    }

}

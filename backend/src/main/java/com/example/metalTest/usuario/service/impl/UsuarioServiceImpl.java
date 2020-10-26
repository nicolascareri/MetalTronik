package com.example.metalTest.usuario.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.cargo.domain.Cargo;
import com.example.metalTest.cargo.repository.CargoRepository;
import com.example.metalTest.common.ordenes.Estado;
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
    private CargoRepository cargoRepository;

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

    @Transactional
    @Override
    public Usuario create(UsuarioRequest usuario) throws ValidateFieldException {
        Usuario UsrActual = usuarioMapper.usuarioRequestToUsuario(usuario);
        if (usuario.getEstado() != Estado.ACTIVO.getValue() && usuario.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(usuario.getEstado()));
        }

        return usuarioRepository.save(UsrActual);
    }

    @Transactional
    @Override
    public Usuario update(Integer id, UsuarioRequest usuario) throws ValidateFieldException {
        Optional<Usuario> op = usuarioRepository.findById(id);
        if (!op.isPresent()) {
            throw new ValidateFieldException("El usuario que desea acceder no existe", "id", String.valueOf(id));
        }
        if (usuario.getEstado() != Estado.ACTIVO.getValue() && usuario.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(usuario.getEstado()));
        }
        Usuario UsrActual = usuarioMapper.usuarioRequestToUsuario(usuario);
        Cargo cargoDeUsuarioActual = cargoRepository.findById(UsrActual.getCargo_id());
        UsrActual.setCargo(cargoDeUsuarioActual);
        UsrActual.setId(id);
        return usuarioRepository.save(UsrActual);
    }

}

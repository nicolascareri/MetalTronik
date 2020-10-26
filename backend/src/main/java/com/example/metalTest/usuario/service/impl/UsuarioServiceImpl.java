package com.example.metalTest.usuario.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.cargo.domain.Cargo;
import com.example.metalTest.cargo.mapper.CargoMapper;
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
        Usuario usrActual = usuarioMapper.usuarioRequestToUsuario(usuario);
        Optional<Cargo> optionalCargo = cargoRepository.findById(usuario.getCargo_id());
        if (usuario.getEstado() != Estado.ACTIVO.getValue() && usuario.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(usuario.getEstado()));
        }
        if (!optionalCargo.isPresent()){
            throw new ValidateFieldException("Cargo no encontrado", "", "");
        }
        Cargo cargo = optionalCargo.get();
        usrActual.setCargo(cargo);
        return usuarioRepository.save(usrActual);
    }

    @Transactional
    @Override
    public Usuario update(Integer id, UsuarioRequest usuario) throws ValidateFieldException {
        Optional<Usuario> op = usuarioRepository.findById(id);
        Optional<Cargo> optionalCargo = cargoRepository.findById(usuario.getCargo_id());
        if (!op.isPresent()) {
            throw new ValidateFieldException("El usuario que desea acceder no existe", "id", String.valueOf(id));
        }
        if (usuario.getEstado() != Estado.ACTIVO.getValue() && usuario.getEstado() != Estado.ELIMINADO.getValue()) {
            throw new ValidateFieldException("Valor en campo invalido", "estado", String.valueOf(usuario.getEstado()));
        }
        if (!optionalCargo.isPresent()){
            throw new ValidateFieldException("Cargo no encontrado", "", "");
        }
        Cargo cargo = optionalCargo.get();
        Usuario usrActual = usuarioMapper.usuarioRequestToUsuario(usuario);
        usrActual.setCargo(cargo);
        usrActual.setId(id);
        return usuarioRepository.save(usrActual);
    }

}

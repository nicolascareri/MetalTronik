package com.example.metalTest.usuario.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.usuario.domain.Usuario;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import com.example.metalTest.usuario.service.UsuarioService;
import net.bytebuddy.implementation.bytecode.Throw;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> getAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario getById(Integer id) throws ValidateFieldException {
        Optional<Usuario> op = usuarioRepository.findById(id);
        if (op.isPresent()){
            return op.get();
        }
        else{
            throw  new ValidateFieldException("El usuario quE desea acceder no existe", "id", id.toString());
        }

    }

    @Override
    public Usuario create(Usuario usuario){
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Integer id, Usuario usuario) throws ValidateFieldException{
        Optional<Usuario> op = usuarioRepository.findById(id);
        if (!op.isPresent()) {
            throw new ValidateFieldException("El usuario que desea acceder no existe", "id", String.valueOf(id));
        }
        Usuario u = op.get();
        u.setNombre(usuario.getNombre());
        u.setApellido(usuario.getApellido());
        u.setDni(usuario.getDni());
        return usuarioRepository.save(u);
    }
}

package com.example.metalTest.usuarios.usuario.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.credencial.repository.CredencialRepository;
import com.example.metalTest.usuarios.rol.domain.Rol;
import com.example.metalTest.usuarios.rol.repository.RolRepository;
import com.example.metalTest.usuarios.usuario.controller.request.UsuarioRequest;
import com.example.metalTest.usuarios.credencial.domain.Credencial;
import com.example.metalTest.usuarios.usuario.domain.Usuario;
import com.example.metalTest.usuarios.usuario.mapper.UsuarioMapper;
import com.example.metalTest.usuarios.usuario.repository.UsuarioRepository;
import com.example.metalTest.usuarios.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private UsuarioMapper usuarioMapper;
    @Autowired
    TipoRepository tipoRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    CredencialRepository credencialRepository;

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
    public Usuario create(UsuarioRequest usuario){
        Usuario usrActual = usuarioMapper.usuarioRequestToUsuario(usuario);
        Set<Rol> roles = new HashSet<>();
        roles.add(rolRepository.findById(usuario.getRol()).get());
        usrActual.setCargo(tipoRepository.findById(usuario.getCargo()).get());
        Integer id_credencial = credencialRepository.save(new Credencial(usrActual.getId(), usuario.getNombre_usuario(), passwordEncoder.encode(usuario.getContrasenia()), roles)).getId();
        usrActual.setCredencial(credencialRepository.findById(id_credencial).get());
        usuarioRepository.save(usrActual);
        return null;
    }

    @Override
    public Usuario update(Integer id, UsuarioRequest usuario){
        Usuario usrActual = usuarioMapper.usuarioRequestToUsuario(usuario);
        usrActual.setCargo(tipoRepository.findById(usuario.getCargo()).get());
        usrActual.setId(id);
        usuarioRepository.save(usrActual);
        return null;
    }

    @Override
    public Usuario findFyNombreUsuario(String s) {
        return usuarioRepository.findByNombreUsuario(s);
    }


}

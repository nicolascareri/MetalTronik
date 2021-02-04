package com.example.metalTest.usuarios.usuario.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tipo.service.TipoService;
import com.example.metalTest.usuarios.credenciales.credencial.domain.Credencial;
import com.example.metalTest.usuarios.credenciales.credencial.repository.CredencialRepository;
import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;
import com.example.metalTest.usuarios.credenciales.rol.repository.RolRepository;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.personal.mapper.PersonalMapper;
import com.example.metalTest.usuarios.personal.repository.PersonalRepository;
import com.example.metalTest.usuarios.usuario.controller.request.UsuarioRequest;
import com.example.metalTest.usuarios.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    TipoService tipoService;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    PersonalMapper personalMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    CredencialRepository credencialRepository;

    @Override
    public Personal create(UsuarioRequest usuarioRequest, Integer id) throws ValidateFieldException {
       Personal personal = personalRepository.findById(id).get();
        Set<Rol> roles = new HashSet<>();
        roles.add(rolRepository.findById(usuarioRequest.getRol()).get());
        Credencial credencial = new Credencial(usuarioRequest.getNombre_usuario(), passwordEncoder.encode(usuarioRequest.getContrasenia()), roles);
        personal.setCredencial(credencialRepository.save(credencial));
        personalRepository.save(personal);
        return personal;
    }

    @Override
    public List<Personal> getAll() {
        return personalRepository.findAllByCredencialIsNotNull();
    }

    @Override
    public Personal getById(Integer id) {
        return personalRepository.findById(id).get();
    }
}

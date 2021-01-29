package com.example.metalTest.usuarios.personal.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.credenciales.credencial.repository.CredencialRepository;
import com.example.metalTest.usuarios.personal.mapper.PersonalMapper;
import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;
import com.example.metalTest.usuarios.credenciales.rol.repository.RolRepository;
import com.example.metalTest.usuarios.personal.controller.request.UsuarioRequest;
import com.example.metalTest.usuarios.credenciales.credencial.domain.Credencial;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.personal.repository.PersonalRepository;
import com.example.metalTest.usuarios.personal.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonalServiceImpl implements PersonalService {

    @Autowired
    private PersonalRepository personalRepository;
    @Autowired
    private PersonalMapper personalMapper;
    @Autowired
    TipoRepository tipoRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    CredencialRepository credencialRepository;

    @Override
    public List<Personal> getAll() {
        return personalRepository.findAll();
    }

    @Override
    public Personal getById(Integer id) throws ValidateFieldException {
        Optional<Personal> op = personalRepository.findById(id);
        if (op.isPresent()) {
            return op.get();
        } else {
            throw new ValidateFieldException("El personal quE desea acceder no existe", "id", id.toString());
        }

    }

    @Override
    public Personal create(UsuarioRequest usuario){
        Personal usrActual = personalMapper.usuarioRequestToUsuario(usuario);
        Set<Rol> roles = new HashSet<>();
        roles.add(rolRepository.findById(usuario.getRol()).get());
        usrActual.setCargo(tipoRepository.findById(usuario.getCargo()).get());
        Credencial credencial = new Credencial(usrActual.getId(), usuario.getNombre_usuario(), passwordEncoder.encode(usuario.getContrasenia()), roles);
        usrActual.setCredencial(credencialRepository.save(credencial));
        return personalRepository.save(usrActual);
    }

    @Override
    public Personal update(Integer id, UsuarioRequest usuario){
        Personal usrActual = personalMapper.usuarioRequestToUsuario(usuario);
        usrActual.setCargo(tipoRepository.findById(usuario.getCargo()).get());
        usrActual.setId(id);
        return  personalRepository.save(usrActual);
    }

    @Override
    public Personal findFyNombreUsuario(String s) {
        return personalRepository.findByCredencial_Nombre_usuario(s).get();
    }

}

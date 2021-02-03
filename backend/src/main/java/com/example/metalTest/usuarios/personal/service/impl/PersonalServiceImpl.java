package com.example.metalTest.usuarios.personal.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.credenciales.credencial.repository.CredencialRepository;
import com.example.metalTest.usuarios.personal.controller.request.PersonalRequest;
import com.example.metalTest.usuarios.personal.domain.Direccion;
import com.example.metalTest.usuarios.personal.mapper.PersonalMapper;
import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;
import com.example.metalTest.usuarios.credenciales.rol.repository.RolRepository;
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
    RolRepository rolRepository;
    @Autowired
    CredencialRepository credencialRepository;

    @Override
    public List<Personal> getAll() {
        return personalRepository.findAllByCredencialIsNull();
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
    public Personal create(PersonalRequest usuario){
        Personal usrActual = personalMapper.personalRequestToPersonal(usuario);
        Direccion direccion = usuario.getDireccion();
        usrActual.setDireccion(new Direccion(direccion.getPais(), direccion.getProvincia(), direccion.getCiudad(), direccion.getCalle(), direccion.getNumero()));
        usrActual.setCargo(tipoRepository.findById(usuario.getCargo()).get());
        return personalRepository.save(usrActual);
    }

    @Override
    public Personal update(Integer id, PersonalRequest usuario){
        Personal usrActual = personalMapper.personalRequestToPersonal(usuario);
        usrActual.setCargo(tipoRepository.findById(usuario.getCargo()).get());
        Direccion direccionReq = usuario.getDireccion();
        Direccion direccion = usrActual.getDireccion();
        direccion.setCalle(direccionReq.getCalle());
        direccion.setCiudad(direccionReq.getCiudad());
        direccion.setNumero(direccionReq.getNumero());
        direccion.setPais(direccionReq.getPais());
        direccion.setProvincia(direccionReq.getProvincia());
        usrActual.setDireccion(direccion);
        usrActual.setId(id);
        return  personalRepository.save(usrActual);
    }

    @Override
    public Personal findFyNombreUsuario(String s) {
        return null;//personalRepository.findByCredencial_Nombre_usuario(s).get();
    }

}

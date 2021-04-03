package com.example.metalTest.usuarios.personal.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.validator.RepositoryValidator;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.credenciales.credencial.repository.CredencialRepository;
import com.example.metalTest.usuarios.personal.controller.request.PersonalRequest;
import com.example.metalTest.usuarios.personal.domain.Direccion;
import com.example.metalTest.usuarios.personal.mapper.PersonalMapper;
import com.example.metalTest.usuarios.credenciales.rol.repository.RolRepository;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.personal.repository.PersonalRepository;
import com.example.metalTest.usuarios.personal.service.PersonalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    RepositoryValidator<Personal> repositoryValidator = new  RepositoryValidator<Personal>();

    @Override
    public List<Personal> getAll() {
        return personalRepository.findAllByCredencialIsNull();
    }

    @Override
    public Personal getById(Integer id) throws ValidateFieldException {
        return repositoryValidator.getObject(personalRepository, id);

    }

    @Override
    public Personal create(PersonalRequest usuario) throws ValidateFieldException {
        Personal usrActual = personalMapper.personalRequestToPersonal(usuario);
        Direccion direccion = usuario.getDireccion();
        usrActual.setDireccion(new Direccion(direccion.getPais(), direccion.getProvincia(), direccion.getCiudad(), direccion.getCalle(), direccion.getNumero()));
        RepositoryValidator<Tipo> tipoRepositoryValidator = new RepositoryValidator<Tipo>();
        usrActual.setCargo(tipoRepositoryValidator.getObject(tipoRepository, usuario.getCargo()));
        return personalRepository.save(usrActual);
    }

    @Override
    public Personal update(Integer id, PersonalRequest usuario) throws ValidateFieldException {
        Personal usrActual = personalMapper.personalRequestToPersonal(usuario);
        RepositoryValidator<Tipo> tipoRepositoryValidator = new RepositoryValidator<Tipo>();
        usrActual.setCargo(tipoRepositoryValidator.getObject(tipoRepository, usuario.getCargo()));
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



}

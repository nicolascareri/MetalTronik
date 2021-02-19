package com.example.metalTest.usuarios.credenciales.rol.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.validator.RepositoryValidator;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.credenciales.permiso.domain.Permiso;
import com.example.metalTest.usuarios.credenciales.permiso.repository.PermisoRepository;
import com.example.metalTest.usuarios.credenciales.permiso.request.PermisoRequest;
import com.example.metalTest.usuarios.credenciales.rol.controller.request.RolRequest;
import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;
import com.example.metalTest.usuarios.credenciales.rol.enums.RolRango;
import com.example.metalTest.usuarios.credenciales.rol.repository.RolRepository;
import com.example.metalTest.usuarios.credenciales.rol.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class RolServiceImpl implements RolService {
    @Autowired
    TipoRepository tipoRepository;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    PermisoRepository permisoRepository;
    @Autowired
    RepositoryValidator repositoryValidator;

    @Override
    public Rol create(RolRequest rolRequest) throws ValidateFieldException {
        Rol rol = new Rol();
        rol.setNombre(rolRequest.getNombre());
        rol.setRango(RolRango.ROLE_PERSONAL);
        List<Permiso> permisos = new ArrayList<>();
        for (PermisoRequest permisoRequest: rolRequest.getPermisos() ) {
            Permiso permiso = new Permiso();
            permiso.setPermiso((Tipo) repositoryValidator.getObject(tipoRepository, permisoRequest.getPermiso()));
            permiso.setSector((Tipo) repositoryValidator.getObject(tipoRepository, permisoRequest.getSector()));
            permisos.add(permiso);
        }
        rol.setPermisos(permisos);
        return rolRepository.save(rol);
    }

    @Override
    public Rol findByRango(String rango) {
        return rolRepository.findByRango(rango).get();
    }

    @Override
    public List<Rol> getAll() {
        return rolRepository.findAll();
    }

    @Override
    public Rol getById(Integer id) throws ValidateFieldException {
        return (Rol) repositoryValidator.getObject(rolRepository, id);
    }
}

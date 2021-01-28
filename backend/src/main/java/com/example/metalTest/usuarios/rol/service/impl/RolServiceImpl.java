package com.example.metalTest.usuarios.rol.service.impl;

import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.permiso.domain.Permiso;
import com.example.metalTest.usuarios.permiso.repository.PermisoRepository;
import com.example.metalTest.usuarios.permiso.request.PermisoRequest;
import com.example.metalTest.usuarios.rol.controller.request.RolRequest;
import com.example.metalTest.usuarios.rol.domain.Rol;
import com.example.metalTest.usuarios.rol.enums.RolRango;
import com.example.metalTest.usuarios.rol.repository.RolRepository;
import com.example.metalTest.usuarios.rol.service.RolService;
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
    @Override
    public Rol create(RolRequest rolRequest) {
        Rol rol = new Rol();
        rol.setNombre(rolRequest.getNombre());
        rol.setRango(RolRango.ROLE_PERSONAL);
        List<Permiso> permisos = new ArrayList<>();
        for (PermisoRequest permisoRequest: rolRequest.getPermisos() ) {
            Permiso permiso = new Permiso();
            permiso.setPermiso(tipoRepository.findById(permisoRequest.getPermiso()).get());
            permiso.setSector(tipoRepository.findById(permisoRequest.getSector()).get());
            permisos.add(permiso);
        }
        rol.setPermisos(permisos);
        return rolRepository.save(rol);
    }

    @Override
    public Rol findByRango(String rango) {
        return rolRepository.findByRango(rango).get();
    }
}

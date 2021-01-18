package com.example.metalTest.usuarios.rol.service.impl;

import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.rol.controller.request.RolRequest;
import com.example.metalTest.usuarios.rol.domain.Rol;
import com.example.metalTest.usuarios.rol.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RolServiceImpl implements RolService {
    @Autowired
    TipoRepository tipoRepository;
    @Override
    public Rol create(RolRequest rolRequest) {
        Rol rol = new Rol();
        rol.setNombre(rolRequest.getNombre());
        return null;
    }
}

package com.example.metalTest.usuarios.credenciales.rol.service;

import com.example.metalTest.usuarios.credenciales.rol.controller.request.RolRequest;
import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;

public interface RolService {
    Rol create(RolRequest rolRequest);

    Rol findByRango(String rango);
}

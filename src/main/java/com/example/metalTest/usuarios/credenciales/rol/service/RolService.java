package com.example.metalTest.usuarios.credenciales.rol.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.usuarios.credenciales.rol.controller.request.RolRequest;
import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;

import java.util.List;

public interface RolService {
    Rol create(RolRequest rolRequest) throws ValidateFieldException;

    Rol findByRango(String rango);

    List<Rol> getAll();

    Rol getById(Integer id) throws ValidateFieldException;
}

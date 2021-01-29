package com.example.metalTest.usuarios.personal.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.usuarios.personal.controller.request.UsuarioRequest;
import com.example.metalTest.usuarios.personal.domain.Personal;

import java.util.List;

public interface PersonalService {
    List<Personal> getAll();

    Personal getById(Integer id) throws ValidateFieldException;

    Personal create(UsuarioRequest usuario) throws ValidateFieldException;

    Personal update(Integer id, UsuarioRequest usuario) throws ValidateFieldException;

    Personal findFyNombreUsuario(String s);
}

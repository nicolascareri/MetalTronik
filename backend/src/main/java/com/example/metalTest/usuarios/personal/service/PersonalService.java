package com.example.metalTest.usuarios.personal.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.usuarios.personal.controller.request.PersonalRequest;
import com.example.metalTest.usuarios.personal.domain.Personal;

import java.util.List;

public interface PersonalService {
    List<Personal> getAll();

    Personal getById(Integer id) throws ValidateFieldException;

    Personal create(PersonalRequest usuario) throws ValidateFieldException;

    Personal update(Integer id, PersonalRequest usuario) throws ValidateFieldException;

    Personal findFyNombreUsuario(String s);
}

package com.example.metalTest.usuarios.usuario.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.security.jwt.JwtDto;
import com.example.metalTest.usuarios.personal.controller.request.LoginRequest;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.usuario.controller.request.UsuarioRequest;

import java.util.List;

public interface UsuarioService {

    Personal create(UsuarioRequest usuarioRequest, Integer id) throws ValidateFieldException;

    List<Personal> getAll();

    Personal getById(Integer id) throws ValidateFieldException;

    JwtDto login(LoginRequest loginRequest);

    Personal findFyNombreUsuario(String s);

}

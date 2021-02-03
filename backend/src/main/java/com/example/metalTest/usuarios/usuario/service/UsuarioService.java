package com.example.metalTest.usuarios.usuario.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.usuario.controller.request.UsuarioRequest;

public interface UsuarioService {

    Personal create(UsuarioRequest usuarioRequest, Integer id) throws ValidateFieldException;
}

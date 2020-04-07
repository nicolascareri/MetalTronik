package com.example.metalTest.usuario.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.usuario.domain.Usuario;

import java.util.List;

public interface UsuarioService {
    List<Usuario> getAll();

    Usuario getById(Integer id) throws ValidateFieldException;

    Usuario create(Usuario usuario) throws ValidateFieldException;

    Usuario update(Integer id, Usuario usuario) throws ValidateFieldException;
}

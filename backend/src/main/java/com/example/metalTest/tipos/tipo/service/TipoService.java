package com.example.metalTest.tipos.tipo.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tipos.tipo.domain.Tipo;

import java.util.List;

public interface TipoService {
    List<Tipo> getAll();

    Tipo getById(Integer id) throws ValidateFieldException;

    Tipo create(Tipo tipo) throws ValidateFieldException;

    Tipo update(Tipo tipo, Integer id) throws ValidateFieldException;
}

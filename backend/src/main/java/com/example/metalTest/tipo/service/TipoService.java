package com.example.metalTest.tipo.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tipo.controller.request.TipoRequest;
import com.example.metalTest.tipo.domain.Tipo;

import java.util.List;

public interface TipoService {
    List<Tipo> getAll();

    Tipo getById(Integer id) throws ValidateFieldException;

    Tipo create(TipoRequest tipoRequest) throws ValidateFieldException;

    Tipo update(TipoRequest tipo, Integer id) throws ValidateFieldException;

    List<Tipo> getByTipo(String tipo);
    List<String> getTipos();
}

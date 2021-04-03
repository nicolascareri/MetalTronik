package com.example.metalTest.maquina.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.controller.request.MaquinaRequest;
import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.maquina.controller.response.MaquinaResponse;
import com.example.metalTest.maquina.domain.Maquina;

import java.util.List;

public interface MaquinaService {
    List<Maquina> getAll();

    MaquinaResponse getById(Integer id) throws ValidateFieldException;

    MaquinaResponse save(MaquinaRequest maquina) throws ValidateFieldException;

    MaquinaResponse update(MaquinaRequest maquinaRequestToMaquina, Integer id) throws ValidateFieldException;

    boolean delete(Integer id) throws Exception;
}

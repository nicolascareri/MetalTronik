package com.example.metalTest.repuestomaquina.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.repuestomaquina.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.repuestomaquina.domain.RepuestoMaquina;

import java.util.List;

public interface RepuestoMaquinaService {
    List<RepuestoMaquina> getAll();

    RepuestoMaquina getById(Integer id) throws ValidateFieldException;

    RepuestoMaquina create(RepuestoMaquinaRequest repuestoMaquinaRequest);

    RepuestoMaquina update(RepuestoMaquinaRequest repuestoMaquinaRequest, Integer id) throws ValidateFieldException;

    List<RepuestoMaquina> getByMaquina(Integer id) throws ValidateFieldException;
}

package com.example.metalTest.maquina.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.controller.request.MaquinaRequest;
import com.example.metalTest.maquina.domain.Maquina;

import java.util.List;

public interface MaquinaService {
    public List<Maquina> getAll();

    public Maquina getById(Integer id) throws ValidateFieldException;

    public Maquina save(MaquinaRequest maquina) throws ValidateFieldException;

    public Maquina update(MaquinaRequest  maquinaRequestToMaquina, Integer id) throws ValidateFieldException;
}

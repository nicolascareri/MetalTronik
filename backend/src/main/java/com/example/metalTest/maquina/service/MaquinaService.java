package com.example.metalTest.maquina.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.controller.request.MaquinaRequest;
import com.example.metalTest.maquina.domain.Maquina;

import java.util.List;

public interface MaquinaService {
    List<Maquina> getAll();

    Maquina getById(Integer id) throws ValidateFieldException;

    Maquina save(MaquinaRequest maquina) throws ValidateFieldException;

    Maquina update(MaquinaRequest maquinaRequestToMaquina, Integer id) throws ValidateFieldException;

    List<Maquina> getAllSinRepuesto();
}

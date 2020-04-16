package com.example.metalTest.maquina.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.domain.Maquina;

import java.util.List;

public interface MaquinaService {
    public List<Maquina> getAll();

    public Maquina getById(Integer id) throws ValidateFieldException;

    public Maquina save(Maquina maquina) throws ValidateFieldException;

    public Maquina getByCod(String cod) throws ValidateFieldException;
}

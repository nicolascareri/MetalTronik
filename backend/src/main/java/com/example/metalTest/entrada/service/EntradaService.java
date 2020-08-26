package com.example.metalTest.entrada.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.entrada.controller.request.EntradaRequest;
import com.example.metalTest.entrada.domain.Entrada;

import java.util.List;

public interface EntradaService {
    List<Entrada> getAll();

    Entrada create(EntradaRequest entradaRequest);

    Entrada getById(Integer id) throws ValidateFieldException;
}

package com.example.metalTest.entrada.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.entrada.controller.request.EntradaRequest;
import com.example.metalTest.entrada.controller.response.EntradaResponse;

import java.util.List;

public interface EntradaService {
    List<EntradaResponse> getAll();

    EntradaResponse create(EntradaRequest entradaRequest);

    EntradaResponse getById(Integer id) throws ValidateFieldException;
}

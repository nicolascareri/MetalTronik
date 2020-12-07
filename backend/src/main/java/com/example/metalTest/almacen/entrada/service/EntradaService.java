package com.example.metalTest.almacen.entrada.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.entrada.controller.request.EntradaRequest;
import com.example.metalTest.almacen.entrada.controller.response.EntradaResponse;

import java.util.List;

public interface EntradaService {
    List<EntradaResponse> getAll();

    EntradaResponse create(EntradaRequest entradaRequest);

    EntradaResponse getById(Integer id) throws ValidateFieldException;
}

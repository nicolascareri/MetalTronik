package com.example.metalTest.almacen.movimiento.entrada.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.movimiento.entrada.controller.request.EntradaRequest;
import com.example.metalTest.almacen.movimiento.entrada.controller.response.EntradaResponse;

import java.util.List;

public interface EntradaService {
    List<EntradaResponse> getAll();

    EntradaResponse create(EntradaRequest entradaRequest) throws ValidateFieldException;

    EntradaResponse getById(Integer id) throws ValidateFieldException;
}

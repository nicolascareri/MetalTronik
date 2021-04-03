package com.example.metalTest.almacen.movimiento.salida.service;

import com.example.metalTest.almacen.movimiento.salida.controller.request.SalidaRequest;
import com.example.metalTest.almacen.movimiento.salida.controller.response.SalidaResponse;
import com.example.metalTest.apiError.exception.ValidateFieldException;

import java.util.List;

public interface SalidaService {
    List<SalidaResponse> getAll();

    SalidaResponse create(SalidaRequest salidaRequest) throws ValidateFieldException;
}

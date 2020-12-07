package com.example.metalTest.almacen.salida.service;

import com.example.metalTest.almacen.salida.controller.request.SalidaRequest;
import com.example.metalTest.almacen.salida.controller.response.SalidaResponse;

import java.util.List;

public interface SalidaService {
    List<SalidaResponse> getAll();

    SalidaResponse create(SalidaRequest salidaRequest);
}

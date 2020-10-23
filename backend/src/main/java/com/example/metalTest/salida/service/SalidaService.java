package com.example.metalTest.salida.service;

import com.example.metalTest.salida.controller.request.SalidaRequest;
import com.example.metalTest.salida.controller.response.SalidaResponse;

import java.util.List;

public interface SalidaService {
    List<SalidaResponse> getAll();

    SalidaResponse create(SalidaRequest salidaRequest);
}

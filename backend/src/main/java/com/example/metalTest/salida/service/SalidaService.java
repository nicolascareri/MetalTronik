package com.example.metalTest.salida.service;

import com.example.metalTest.salida.controller.request.SalidaRequest;
import com.example.metalTest.salida.domain.Salida;

import java.util.List;

public interface SalidaService {
    List<Salida> getAll();

    Salida create(SalidaRequest salidaRequest);
}

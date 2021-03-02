package com.example.metalTest.almacen.ajusteStock.service;

import com.example.metalTest.almacen.ajusteStock.controller.request.AjusteRequest;
import com.example.metalTest.almacen.ajusteStock.controller.response.AjusteResponse;
import com.example.metalTest.apiError.exception.ValidateFieldException;

import java.util.List;

public interface AjusteService {
    List<AjusteResponse> getAll();

    AjusteResponse create(AjusteRequest correccionHistorial, Integer repuesto_id) throws ValidateFieldException;
}

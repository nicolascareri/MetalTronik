package com.example.metalTest.almacen.repuesto.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.almacen.repuesto.controller.response.RepuestoReducidoResponse;
import com.example.metalTest.almacen.repuesto.controller.response.RepuestoResponse;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;

import java.util.List;

public interface RepuestoService {
    List<RepuestoReducidoResponse> getAll();

    RepuestoResponse getById(Integer id) throws ValidateFieldException;

    Repuesto create(RepuestoRequest repuestoRequest);

    Repuesto update(RepuestoRequest repuestoRequest, Integer id) throws ValidateFieldException;

    List<Repuesto> getByMaquina(Integer id);

}

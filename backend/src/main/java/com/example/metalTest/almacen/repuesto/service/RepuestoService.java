package com.example.metalTest.almacen.repuesto.service;

import com.example.metalTest.almacen.repuesto.controller.request.AsociarList;
import com.example.metalTest.almacen.repuesto.controller.response.AsociacionResponse;
import com.example.metalTest.almacen.repuesto.domain.Asociacion;
import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.almacen.repuesto.controller.response.RepuestoResponse;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;

import java.util.List;

public interface RepuestoService {
    List<Repuesto> getAll();

    RepuestoResponse getById(Integer id) throws ValidateFieldException;

    Repuesto create(RepuestoRequest repuestoRequest);

    Repuesto update(RepuestoRequest repuestoRequest, Integer id) throws ValidateFieldException;
    void asociar(AsociarList asociarList);

    List<Asociacion> getVinculados();

}

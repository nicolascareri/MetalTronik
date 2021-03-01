package com.example.metalTest.mantenimientos.correctivo.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.mantenimientos.correctivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.mantenimientos.correctivo.controller.response.MantenimientoCorrectivoResponse;

import java.util.List;

public interface MantenimientoCorrectivoService {
    List<MantenimientoCorrectivoResponse> getAll();

    MantenimientoCorrectivoResponse create(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest) throws ValidateFieldException;

    MantenimientoCorrectivoResponse update(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest, Integer id) throws ValidateFieldException;

    MantenimientoCorrectivoResponse getById(Integer id) throws ValidateFieldException;

}

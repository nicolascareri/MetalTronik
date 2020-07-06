package com.example.metalTest.mantenimientoCorrectivo.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.mantenimientoCorrectivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;

import java.util.List;

public interface MantenimientoCorrectivoService {
    List<MantenimientoCorrectivo> getAll();

    MantenimientoCorrectivo create(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest);

    MantenimientoCorrectivo update(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest, Integer id) throws ValidateFieldException;

    MantenimientoCorrectivo getById(Integer id) throws ValidateFieldException;
}

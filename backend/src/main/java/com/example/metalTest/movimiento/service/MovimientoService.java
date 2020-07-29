package com.example.metalTest.movimiento.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.movimiento.controller.request.MovimientoRequest;
import com.example.metalTest.movimiento.domain.Movimiento;

import java.util.List;

public interface MovimientoService {
    List<Movimiento> getAll();

    Movimiento create(MovimientoRequest movimientoRequest) throws ValidateFieldException;

    Movimiento update(MovimientoRequest movimientoRequest, Integer id);

    List<Movimiento> getByTipo(Short tipo) throws ValidateFieldException;
}

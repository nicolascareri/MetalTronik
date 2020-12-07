package com.example.metalTest.almacen.movimiento.service;

import com.example.metalTest.almacen.movimiento.controller.request.MovimientoRequest;
import com.example.metalTest.almacen.movimiento.domain.Movimiento;

import java.util.List;

public interface MovimientoService {
    List<Movimiento> getAll();

    Movimiento create(MovimientoRequest movimientoRequest);

    Movimiento update(MovimientoRequest movimientoRequest, Integer id);
}

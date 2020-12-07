package com.example.metalTest.almacen.movimiento.mapper;

import com.example.metalTest.almacen.movimiento.controller.request.MovimientoRequest;
import com.example.metalTest.almacen.movimiento.domain.Movimiento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    Movimiento movimientoRequestToMovimiento (MovimientoRequest movimientoRequest);
}

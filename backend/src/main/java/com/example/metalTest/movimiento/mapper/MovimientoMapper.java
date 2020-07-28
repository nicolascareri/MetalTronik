package com.example.metalTest.movimiento.mapper;

import com.example.metalTest.movimiento.controller.request.MovimientoRequest;
import com.example.metalTest.movimiento.domain.Movimiento;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MovimientoMapper {
    Movimiento movimientoRequestToMovimiento (MovimientoRequest movimientoRequest);
}

package com.example.metalTest.almacen.movimiento.salida.mapper;

import com.example.metalTest.almacen.movimiento.salida.controller.request.SalidaRequest;
import com.example.metalTest.almacen.movimiento.salida.controller.response.SalidaResponse;
import com.example.metalTest.almacen.movimiento.salida.domain.Salida;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalidaMapper {
    Salida salidaRequestToSalida(SalidaRequest salidaRequest);
    SalidaResponse toSalidaResponse(Salida salida);
    List<SalidaResponse> toSalidaResponseList(List<Salida> salidaList);
}

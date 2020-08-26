package com.example.metalTest.salida.mapper;

import com.example.metalTest.salida.controller.request.SalidaRequest;
import com.example.metalTest.salida.domain.Salida;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SalidaMapper {
    Salida salidaRequestToSalida(SalidaRequest salidaRequest);
}

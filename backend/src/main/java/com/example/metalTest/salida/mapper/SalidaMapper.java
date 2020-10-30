package com.example.metalTest.salida.mapper;

import com.example.metalTest.salida.controller.request.SalidaRequest;
import com.example.metalTest.salida.controller.response.SalidaResponse;
import com.example.metalTest.salida.domain.Salida;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SalidaMapper {
    Salida salidaRequestToSalida(SalidaRequest salidaRequest);
    SalidaResponse toSalidaResponse(Salida salida);
    List<SalidaResponse> toSalidaResponseList(List<Salida> salidaList);
}

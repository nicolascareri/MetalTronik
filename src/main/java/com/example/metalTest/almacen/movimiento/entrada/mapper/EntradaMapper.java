package com.example.metalTest.almacen.movimiento.entrada.mapper;

import com.example.metalTest.almacen.movimiento.entrada.controller.request.EntradaRequest;
import com.example.metalTest.almacen.movimiento.entrada.controller.response.EntradaResponse;
import com.example.metalTest.almacen.movimiento.entrada.domain.Entrada;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface EntradaMapper {
    Entrada entradaRequestToEntrada(EntradaRequest entradaRequest);
    EntradaResponse toEntradaResponse(Entrada entrada);
    List<EntradaResponse> toEntradaResponseList(List<Entrada> entradaList);
}

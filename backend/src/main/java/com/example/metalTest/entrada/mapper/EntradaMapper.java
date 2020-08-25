package com.example.metalTest.entrada.mapper;

import com.example.metalTest.entrada.controller.request.EntradaRequest;
import com.example.metalTest.entrada.domain.Entrada;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface EntradaMapper {
    Entrada entradaRequestToEntrada(EntradaRequest entradaRequest);
}

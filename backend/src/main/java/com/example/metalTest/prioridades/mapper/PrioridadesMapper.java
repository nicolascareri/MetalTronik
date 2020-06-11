package com.example.metalTest.prioridades.mapper;

import com.example.metalTest.prioridades.controller.request.PrioridadesRequest;
import com.example.metalTest.prioridades.domain.Prioridades;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PrioridadesMapper {
    Prioridades prioridadesRequestToPrioridades(PrioridadesRequest prioridadesRequest);
}

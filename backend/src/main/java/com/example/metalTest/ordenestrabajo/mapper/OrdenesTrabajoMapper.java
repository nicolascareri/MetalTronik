package com.example.metalTest.ordenestrabajo.mapper;

import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrdenesTrabajoMapper {
    OrdenesTrabajo ordenesTrabajoRequestToOrdenesTrabajo(OrdenesTrabajoRequest ordenesTrabajoRequest);
}

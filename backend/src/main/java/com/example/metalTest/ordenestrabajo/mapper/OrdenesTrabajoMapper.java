package com.example.metalTest.ordenestrabajo.mapper;

import com.example.metalTest.ordenestrabajo.controller.request.OrdenesTrabajoRequest;
import com.example.metalTest.ordenestrabajo.controller.response.OrdenesTrabajoResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrdenesTrabajoMapper {
    OrdenesTrabajo ordenesTrabajoRequestToOrdenesTrabajo(OrdenesTrabajoRequest ordenesTrabajoRequest);
    OrdenesTrabajoResponse toOrdenesTrabajoResponse(OrdenesTrabajo ordenesTrabajo);
    List<OrdenesTrabajoResponse> toOrdenesTrabajoResponseList(List<OrdenesTrabajo> ordenesTrabajosList);
}

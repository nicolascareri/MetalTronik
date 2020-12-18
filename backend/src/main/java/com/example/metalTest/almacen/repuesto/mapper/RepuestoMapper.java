package com.example.metalTest.almacen.repuesto.mapper;

import com.example.metalTest.almacen.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.almacen.repuesto.controller.response.RepuestoReducidoResponse;
import com.example.metalTest.almacen.repuesto.controller.response.RepuestoResponse;
import com.example.metalTest.almacen.repuesto.controller.response.RepuestoVinculadoResponse;
import com.example.metalTest.almacen.repuesto.domain.Repuesto;
import com.example.metalTest.parte.repository.ParteRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = ParteRepository.class)
public interface RepuestoMapper {

    Repuesto repuestoRequestToRepuesto(RepuestoRequest repuestoRequest);

    RepuestoReducidoResponse toRepuestoReducidoResponse(Repuesto repuesto);

    List<RepuestoReducidoResponse> toRepuestoReducidoResponseList(List<Repuesto> all);

    RepuestoResponse toRepuestoResponse(Repuesto repuesto);

    RepuestoVinculadoResponse repuestoToRepuestoVinculadoResponse(Repuesto repuesto);
}

package com.example.metalTest.mantenimientos.correctivo.mapper;

import com.example.metalTest.mantenimientos.correctivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.mantenimientos.correctivo.controller.response.MantenimientoCorrectivoResponse;
import com.example.metalTest.mantenimientos.correctivo.domain.MantenimientoCorrectivo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MantenimientoCorrectivoMapper {
    MantenimientoCorrectivo mantenimientoCorrectivoRequestToMantenimientoCorrectivo(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest);

    MantenimientoCorrectivoResponse toMantenimientoCorrectivoResponse(MantenimientoCorrectivo mantenimientoCorrectivo);

    List<MantenimientoCorrectivoResponse> toMantenimientoCorrectivoResponseList(List<MantenimientoCorrectivo> mantenimientoCorrectivos);

}

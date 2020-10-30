package com.example.metalTest.mantenimientoCorrectivo.mapper;

import com.example.metalTest.mantenimientoCorrectivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.mantenimientoCorrectivo.controller.response.MantenimientoCorrectivoResponse;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MantenimientoCorrectivoMapper {
    MantenimientoCorrectivo mantenimientoCorrectivoRequestToMantenimientoCorrectivo(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest);

    MantenimientoCorrectivoResponse toMantenimientoCorrectivoResponse(MantenimientoCorrectivo mantenimientoCorrectivo);

    List<MantenimientoCorrectivoResponse> toMantenimientoCorrectivoResponseList(List<MantenimientoCorrectivo> mantenimientoCorrectivos);

}

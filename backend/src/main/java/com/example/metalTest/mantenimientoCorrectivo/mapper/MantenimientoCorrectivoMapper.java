package com.example.metalTest.mantenimientoCorrectivo.mapper;

import com.example.metalTest.mantenimientoCorrectivo.controller.request.MantenimientoCorrectivoRequest;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MantenimientoCorrectivoMapper {
    MantenimientoCorrectivo mantenimientoCorrectivoRequestToMantenimientoCorrectivo(MantenimientoCorrectivoRequest mantenimientoCorrectivoRequest);
}

package com.example.metalTest.planta.mapper;

import com.example.metalTest.planta.controller.request.PlantaRequest;
import com.example.metalTest.planta.domain.Planta;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlantaMapper {
    Planta plantaRequestToPlanta(PlantaRequest plantaRequest);
}

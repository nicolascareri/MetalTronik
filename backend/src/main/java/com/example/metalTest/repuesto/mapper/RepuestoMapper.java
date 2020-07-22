package com.example.metalTest.repuesto.mapper;

import com.example.metalTest.repuesto.controller.request.RepuestoRequest;
import com.example.metalTest.repuesto.domain.Repuesto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RepuestoMapper {

    Repuesto repuestoRequestToRepuesto(RepuestoRequest repuestoRequest);

}

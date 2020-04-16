package com.example.metalTest.maquina.mapper;

import com.example.metalTest.maquina.controller.response.MaquinaRequest;
import com.example.metalTest.maquina.domain.Maquina;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MaquinaMapper {
    Maquina maquinaRequestToMaquina(MaquinaRequest maquinaRequest);
}

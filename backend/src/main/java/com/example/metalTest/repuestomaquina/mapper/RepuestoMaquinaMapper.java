package com.example.metalTest.repuestomaquina.mapper;

import com.example.metalTest.repuestomaquina.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.repuestomaquina.domain.RepuestoMaquina;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RepuestoMaquinaMapper {

    RepuestoMaquina repuestoMaquinaRequestToRepuestoMaquina(RepuestoMaquinaRequest repuestoMaquinaRequest);

}

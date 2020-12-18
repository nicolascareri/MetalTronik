package com.example.metalTest.maquina.mapper;

import com.example.metalTest.maquina.controller.request.MaquinaRequest;
import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.maquina.controller.response.MaquinaResponse;
import com.example.metalTest.maquina.domain.Maquina;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MaquinaMapper {

    Maquina maquinaRequestToMaquina(MaquinaRequest maquinaRequest);

    MaquinaResponse toMaquinaResponse(Maquina maquina);

    MaquinaReducidoResponse toMaquinaReducidoResponse(Maquina maquina);

    List<MaquinaReducidoResponse> toMaquinaReducidoResponseList(List<Maquina> all);
}

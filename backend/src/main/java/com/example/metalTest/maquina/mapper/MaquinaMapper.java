package com.example.metalTest.maquina.mapper;

import com.example.metalTest.maquina.controller.request.MaquinaRequest;
import com.example.metalTest.maquina.controller.response.MaquinaResponse;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.repuestoMaquina.domain.RepuestoMaquina;
import com.example.metalTest.repuestoMaquina.mapper.RepuestoMaquinaMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {RepuestoMaquinaMapper.class})
public interface MaquinaMapper {

    Maquina maquinaRequestToMaquina(MaquinaRequest maquinaRequest);

    @Mapping(target = "repuestoMaquinaList", source = "repuestoMaquinaList", qualifiedByName = "mapListRepuestoMaquinaResponse")
    MaquinaResponse toMaquinaResponse(Maquina maquina);
}

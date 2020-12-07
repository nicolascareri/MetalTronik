package com.example.metalTest.almacen.repuestoMaquina.mapper;

import com.example.metalTest.maquina.mapper.MaquinaMapper;
import com.example.metalTest.almacen.repuesto.mapper.RepuestoMapper;
import com.example.metalTest.almacen.repuestoMaquina.controller.response.RepuestoMaquinaResponse;
import com.example.metalTest.almacen.repuestoMaquina.domain.RepuestoMaquina;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {RepuestoMapper.class, MaquinaMapper.class})
public interface RepuestoMaquinaMapper {

    @Mapping(target = "repuesto", source = "repuestoMaquinaPk.repuesto", qualifiedByName = "mapRepuestoReducidoResponse")
    @Mapping(target = "maquina", source = "repuestoMaquinaPk.maquina", qualifiedByName = "mapMaquinaReducidoResponse")
    RepuestoMaquinaResponse toRepuestoMaquinaResponse(RepuestoMaquina repuestoMaquina);

    @Named("mapListRepuestoMaquinaResponse")
    List<RepuestoMaquinaResponse> repuestoMaquinaListToRepuestoMaquinaResponseList(List<RepuestoMaquina> repuestoMaquinaList);
}

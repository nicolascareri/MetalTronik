package com.example.metalTest.repuestoMaquina.mapper;

import com.example.metalTest.maquina.mapper.MaquinaMapper;
import com.example.metalTest.repuestoMaquina.controller.response.RepuestoMaquinaResponse;
import com.example.metalTest.repuestoMaquina.domain.RepuestoMaquina;
import com.example.metalTest.usuario.mapper.UsuarioMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = {UsuarioMapper.class, MaquinaMapper.class})
public interface RepuestoMaquinaMapper {

    @Mapping(target = "repuesto", source = "repuestoMaquinaPk.repuesto", qualifiedByName = "mapRepuestoReducidoResponse")
    @Mapping(target = "maquina", source = "repuestoMaquinaPk.maquina", qualifiedByName = "mapMaquinaReducidoResponse")
    RepuestoMaquinaResponse toRepuestoMaquinaResponse(RepuestoMaquina repuestoMaquina);

    @Named("mapListRepuestoMaquinaResponse")
    List<RepuestoMaquinaResponse> repuestoMaquinaListToRepuestoMaquinaResponseList(List<RepuestoMaquina> repuestoMaquinaList);
}

package com.example.metalTest.tipos.tipo.mapper;

import com.example.metalTest.tipos.tipo.controller.request.TipoRequest;
import com.example.metalTest.tipos.tipo.domain.Tipo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoMapper {
    Tipo tipoRequestToTipo(TipoRequest tipo);
}

package com.example.metalTest.tipo.mapper;

import com.example.metalTest.tipo.controller.request.TipoRequest;
import com.example.metalTest.tipo.domain.Tipo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TipoMapper {
    Tipo tipoRequestToTipo(TipoRequest tipo);
}

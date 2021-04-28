package com.example.metalTest.almacen.asociacion.mapper;


import org.mapstruct.Mapper;
import com.example.metalTest.almacen.asociacion.controller.request.AsociarList;
import com.example.metalTest.almacen.asociacion.controller.response.AsociacionResponse;
import com.example.metalTest.almacen.asociacion.domain.Asociacion;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AsociacionMapper {
    Asociacion asociacionRequestToAsociacion(AsociarList asociarList);
    AsociacionResponse toAsociacionResponse(Asociacion asociacion);
    List<AsociacionResponse> toAsociacionResponseList(List<Asociacion> asociaciones);
    AsociacionResponse toAsociacionResponse(AsociarList object);
}
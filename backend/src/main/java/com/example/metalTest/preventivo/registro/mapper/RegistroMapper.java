package com.example.metalTest.preventivo.registro.mapper;

import com.example.metalTest.preventivo.registro.controller.response.RegistroResponse;
import com.example.metalTest.preventivo.registro.controller.response.RegistroResponseParaListado;
import com.example.metalTest.preventivo.registro.domain.Registro;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RegistroMapper {
    RegistroResponse toRegistroResponse(Registro registro);

    List<RegistroResponse> toRegistroResponseList (List<Registro> registroList);

    RegistroResponseParaListado toRegistroResponseParaListado(Registro registro);

    List<RegistroResponseParaListado> toRegistroResponseParaListadoList (List<Registro> registroList);
}

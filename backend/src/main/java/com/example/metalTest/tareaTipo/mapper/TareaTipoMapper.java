package com.example.metalTest.tareaTipo.mapper;

import com.example.metalTest.tareaTipo.controller.request.TareaTipoRequest;
import com.example.metalTest.tareaTipo.controller.response.TareaTipoResponse;
import com.example.metalTest.tareaTipo.domain.TareaTipo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TareaTipoMapper {
    TareaTipo tareaTipoRequestToTareaTipo(TareaTipoRequest tareaTipoRequest);
}

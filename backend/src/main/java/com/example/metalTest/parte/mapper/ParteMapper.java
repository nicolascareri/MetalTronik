package com.example.metalTest.parte.mapper;

import com.example.metalTest.parte.controller.request.ParteRequest;
import com.example.metalTest.parte.controller.response.ParteResponse;
import com.example.metalTest.parte.domain.Parte;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ParteMapper {
    Parte parteRequestToParte(ParteRequest parteRequest);

    List<Parte> ParteRequestListToParteList(List<ParteRequest> parteRequestList);

    ParteResponse toParteResponse (Parte parte);

    List<ParteResponse> toParteResponseList (List<Parte> parteLista);
}

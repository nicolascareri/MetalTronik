package com.example.metalTest.indicadores.mapper;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;

import java.util.List;

public interface IndicadoresMapper{

    List<IndicatorResponse> toIndicadoresResponseFormula1Usuario(List<OrdenesTrabajo> consult);

    List<IndicatorResponse> toIndicadoresResponseFormula1Sector(List<OrdenesTrabajo> consult);

    List<IndicatorResponse> toIndicadoresResponseFormula2(List<OrdenesTrabajo> consult);

}

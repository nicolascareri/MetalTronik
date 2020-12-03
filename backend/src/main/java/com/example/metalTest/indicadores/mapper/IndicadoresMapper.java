package com.example.metalTest.indicadores.mapper;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.indicadores.controller.response.Torta;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;

import java.util.List;

public interface IndicadoresMapper{

    List<IndicatorResponse> toIndicadoresResponseFormula1(List<String[]> consult);

    Torta toTorta(List<String[]> consult);

}

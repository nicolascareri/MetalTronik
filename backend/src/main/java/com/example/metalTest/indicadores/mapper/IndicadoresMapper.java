package com.example.metalTest.indicadores.mapper;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

public interface IndicadoresMapper{

    List<IndicatorResponse> toIndicadoresResponseFormula1Usuario(List<OrdenesTrabajo> consult);

    List<IndicatorResponse> toIndicadoresResponseFormula2(List<OrdenesTrabajo> consult);

}

package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;

import java.util.List;

public interface Formula {
    List<IndicatorResponse> getResultadoUsuario(List<OrdenesTrabajo> consult);

    List<IndicatorResponse> getResultadoSector(List<OrdenesTrabajo> consult);
}

package com.example.metalTest.indicadores.service;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.indicadores.controller.response.Torta;

import java.util.List;

public interface IndicadoresService {
    List<IndicatorResponse> getIndicatorsForm1Personal();

    List<IndicatorResponse> getIndicatorsForm1Sector();

    List<IndicatorResponse> getIndicatorForm2Personal();

    List<IndicatorResponse> getIndicatorForm2Sector();

    Torta getGrafTortaTipo();

    Torta getGrafTortaPrioridad();

    List<IndicatorResponse> getLineChartMaquina();
}

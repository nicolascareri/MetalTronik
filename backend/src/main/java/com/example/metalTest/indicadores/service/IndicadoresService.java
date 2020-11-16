package com.example.metalTest.indicadores.service;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;

import java.text.ParseException;
import java.util.List;

public interface IndicadoresService {
    List<IndicatorResponse> getIndicatorsForm1Usuario();

    List<IndicatorResponse> getIndicatorsForm1Sector();

    List<IndicatorResponse> getIndicatorForm2Usuario();

    List<IndicatorResponse> getIndicatorForm2Sector();
}

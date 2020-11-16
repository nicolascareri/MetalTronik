package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;

import java.util.List;

public interface Formula {
    List<IndicatorResponse> getResultado(List<String> consult);
}

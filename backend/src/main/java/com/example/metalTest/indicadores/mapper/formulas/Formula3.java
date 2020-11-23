package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;

import java.util.List;

public class Formula3 extends Formula {
    @Override
    public List<IndicatorResponse> getResultadoUsuario(List<OrdenesTrabajo> consult) {
        return null;
    }

    @Override
    public List<IndicatorResponse> getResultadoSector(List<OrdenesTrabajo> consult) {
        return null;
    }

    @Override
    IndicatorResponse getUsuario(List<OrdenesTrabajo> ordenes) {
        return null;
    }

    @Override
    IndicatorResponse getSector(List<OrdenesTrabajo> ordenes) {
        return null;
    }

    @Override
    int getFormula(int a, int b) {
        return 0;
    }
}

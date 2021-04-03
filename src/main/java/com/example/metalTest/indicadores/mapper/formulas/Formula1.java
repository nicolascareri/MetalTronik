package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;

import java.util.ArrayList;
import java.util.List;

public class Formula1 extends Formula {
    public Formula1() {
        
    }

    @Override
    public List<IndicatorResponse> getResultado(List<String[]> consult) {

        return getResultados(consult);
    }




    


}

package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;

import java.util.ArrayList;
import java.util.List;

public abstract class Formula {

    public abstract List<IndicatorResponse> getResultado(List<String[]> consult);
    List<IndicatorResponse> getResultados(List<String[]> consult){
        List<IndicatorResponse> indicadores = new ArrayList<>();
        for(Object[] ob : consult){
            String nombre = String.valueOf(ob[0]);
            Integer totales= Integer.valueOf((String)ob[1]);
            Integer ordenesOk= Integer.valueOf((String)ob[2]);
            indicadores.add(getIndicador(getFormula(totales, ordenesOk), nombre));
        }
        return indicadores;
    }
    int getFormula(int totales, int ordenesOk){
        return (ordenesOk*100/totales);
    }

    IndicatorResponse getIndicador(Integer data, String label){
        IndicatorResponse indicador = new IndicatorResponse();
        List<Integer> a = new ArrayList<>();
        a.add(data);
        indicador.setData(a);
        indicador.setLabel(label);
        return indicador;
    }



}

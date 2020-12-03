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

    private List<IndicatorResponse> getResultados(List<String[]> consult){
        List<IndicatorResponse> indicadores = new ArrayList<>();
        for(Object[] ob : consult){
            String nombre = String.valueOf(ob[0]);
            Integer totales= Integer.valueOf((String)ob[1]);
            Integer ordenesOk= Integer.valueOf((String)ob[2]);
            indicadores.add(getIndicador(getFormula(totales, ordenesOk), nombre));
        }
        return indicadores;
    }
    private int getFormula(int totales, int ordenesOk){
        return (ordenesOk*100/totales);
    }

    private IndicatorResponse getIndicador(Integer data, String label){
        IndicatorResponse indicador = new IndicatorResponse();
        List<Integer> a = new ArrayList<>();
        a.add(data);
        indicador.setData(a);
        indicador.setLabel(label);
        return indicador;
    }


    


}

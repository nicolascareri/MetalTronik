package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;


@NoArgsConstructor
public class LineChartMaquina{

    public List<IndicatorResponse> getResultados(List<String[]> consult){
        List<IndicatorResponse> indicadores = new ArrayList<>();
        String cod = consult.get(0)[0];
        IndicatorResponse actual = getNewIndicador(cod);
        indicadores.add(actual);
        for (String[] a: consult) {
            cod = a[0];
            if(cod.compareTo(indicadores.get(indicadores.size()-1).getLabel()) == 0  ){
                double aux = Double.valueOf(a[3]);
                int aux1 =(int) aux;
                indicadores.get(indicadores.size()-1).getData().set(aux1-1,getFormula(Integer.valueOf(a[1]),Integer.valueOf(a[2])));
            }else{
                double aux = Double.valueOf(a[3]);
                int aux1 =(int) aux;
                indicadores.add(getNewIndicador(a[0]));
                indicadores.get(indicadores.size()-1).getData().set(aux1-1,getFormula(Integer.valueOf(a[1]),Integer.valueOf(a[2])));
            }
        }
        return indicadores;
    }


    private List<Integer> initData(){
        List<Integer> data = new ArrayList<>();
        for (int i = 0; i <12; i++){
            data.add(0);
        }
        return data;
    }

    private IndicatorResponse getNewIndicador(String label){
        IndicatorResponse indicador_actual = new IndicatorResponse();
        indicador_actual.setLabel(label);
        indicador_actual.setData(initData());
        return indicador_actual;
    }

    int getFormula(int totales, int dias){
        if(dias == 0) dias = 1;
        return (dias*24/totales);
    }

}

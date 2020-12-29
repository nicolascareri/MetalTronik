package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.LineChart;

import java.util.ArrayList;
import java.util.List;

public class LineChartMaquina{

    List<LineChart> getResultados(List<String[]> consult){
        List<LineChart> indicadores = new ArrayList<>();
        for(Object[] ob : consult){
            String maquina_cod = String.valueOf(ob[0]);
            Integer totales= Integer.valueOf((String)ob[1]);
            Integer dias= Integer.valueOf((String)ob[2]);
            Integer mes= Integer.valueOf((String)ob[3]);
            indicadores.add(getIndicador(getFormula(totales, dias), maquina_cod));
        }
        return indicadores;
    }

    List<String[]> getPorMaquina(List<String[]> consult){
        List<String[]> list = new ArrayList<>();
        String maquina_cod = String.valueOf(consult.get(0)[0]);
        for (String[] ob : consult) {
            String maquina_actual = String.valueOf(ob[0]);
            if ( maquina_cod == maquina_actual){
                list.add(ob);
            }
        }
        return null;
    }

    int getFormula(int totales, int ordenesOk){
        return (ordenesOk*100/totales);
    }

    LineChart getIndicador(Integer data, String label){
        LineChart indicador = new LineChart();
        List<Integer> a = new ArrayList<>();
        a.add(data);
        indicador.setData(a);
        return indicador;
    }
}

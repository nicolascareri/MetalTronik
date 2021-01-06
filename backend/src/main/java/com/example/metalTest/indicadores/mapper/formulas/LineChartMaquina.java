package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.LineChart;
import lombok.NoArgsConstructor;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@NoArgsConstructor
public class LineChartMaquina{

    public List<LineChart> getResultados(List<String[]> consult){
        List<LineChart> indicadores = new ArrayList<>();
        for(Object[] ob : consult){
            String maquina_cod = String.valueOf(ob[0]);
            Integer totales= Integer.valueOf((String)ob[1]);
            String fecha_fin= String.valueOf(ob[2]);
            String fecha_inicio= String.valueOf(ob[3]);
            indicadores.add(getIndicador(getFormula(totales, getDiasEntreFechas(fecha_fin, fecha_inicio)), maquina_cod, fecha_fin));
        }
        return indicadores;
    }

    private Integer getDiasEntreFechas(String fin, String inicio){
        Integer resultado = null;
        try {
            Date fFin=new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(fin);
            Date fInicio=new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(inicio);
            resultado = (int)Duration.between(fInicio.toInstant(),fFin.toInstant()).toDays();
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultado;
    }


    int getFormula(int totales, int ordenesOk){
        if(ordenesOk == 0) ordenesOk = 1;
        return (ordenesOk*24/totales);
    }

    LineChart getIndicador(Integer data, String label, String mes){
        LineChart indicador = new LineChart();
        List<Integer> a = new ArrayList<>();
        a.add(data);
        indicador.setData(a);
        indicador.setLabel(label);
        Integer nroMes= null;
        try{
           nroMes = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(mes).getMonth();
        }catch (ParseException e) {e.printStackTrace();}
        indicador.setMes(nroMes+1);
        return indicador;
    }
}

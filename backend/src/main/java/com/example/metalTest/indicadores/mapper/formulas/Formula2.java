package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.indicadores.mapper.ToIndicadoresMapper;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class Formula2  extends ToIndicadoresMapper implements Formula {

    public Formula2() {
    }

    @Override
    public List<IndicatorResponse> getResultado(List<String> consulta) {
        return getIndicadoresFormula2(consulta);
    }

    private List<IndicatorResponse> getIndicadoresFormula2(List<String> consulta) {
        List<IndicatorResponse> a = new ArrayList<>();
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date fechaFin = sdf.parse("2020-11-12");
            Date fechaEntrega = sdf.parse("2019-11-12");
            long diffInMillies = Math.abs(fechaEntrega.getTime() - fechaFin.getTime());
            long diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
            System.out.println("daleeeee"+ diff);
        }catch (Exception e){
            System.out.println( "alto error pa: " +e);
        }
        return a;
    }

    private int diasTotales(int dia1, int mes1, int año1, int dia2, int mes2, int año2){
        return 0;
    }

    private int añosToDias(int años){
        if (años >= 0)
            return 0;
        else
            return años * 365;
    }

    private int diferenciaAñosADias(int actual, int añoARealizar){

        return 0;

    }

    private int diferenciaDias(int mes1, int mes2){
        if (mes1>mes2){
            return mes1-mes2;
        }else{
            return mes2-mes1;
        }
    }

    private int mesToDias(int mes){
        switch (mes) {
            case 1, 3, 5, 7, 8, 10, 12:
                //31
                return 31;
            case 4,6,9,11:
                //30
                return 30;
            default:
                return  28;
        }
    }



}

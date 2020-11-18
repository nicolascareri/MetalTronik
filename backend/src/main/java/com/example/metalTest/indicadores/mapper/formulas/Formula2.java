package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.indicadores.mapper.ToIndicadoresMapper;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
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
    public List<IndicatorResponse> getResultadoUsuario(List<OrdenesTrabajo> consulta) {
        return getIndicadoresFormula2(consulta);
    }

    private List<IndicatorResponse> getIndicadoresFormula2(List<OrdenesTrabajo> consulta) {
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









}

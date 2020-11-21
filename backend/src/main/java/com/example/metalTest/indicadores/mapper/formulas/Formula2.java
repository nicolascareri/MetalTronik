package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import org.springframework.stereotype.Component;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

@Component
public class Formula2  extends Formula {

    public Formula2() {
    }

    @Override
    public List<IndicatorResponse> getResultadoUsuario(List<OrdenesTrabajo> consulta) {
        return getIndicadoresUsuario(consulta);
    }

    @Override
    public List<IndicatorResponse> getResultadoSector(List<OrdenesTrabajo> consult) {
        return getIndicadoresSector(consult);
    }

    @Override
    public IndicatorResponse getUsuario(List<OrdenesTrabajo> ordenes) {
        return null;
    }

    @Override
    IndicatorResponse getSector(List<OrdenesTrabajo> ordenes) {
        return null;
    }

    @Override
    int getFormula(int a, int b) {
        return a/b;
    }


    private int getDias(String fin, String entrega){
        long diff = 0;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
            Date fechaFin = sdf.parse(fin);
            Date fechaEntrega = sdf.parse(entrega);
            long diffInMillies = Math.abs(fechaEntrega.getTime() - fechaFin.getTime());
            diff = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
        }catch (Exception e){
            System.out.println(e);
        }
        return 1;
    }








}

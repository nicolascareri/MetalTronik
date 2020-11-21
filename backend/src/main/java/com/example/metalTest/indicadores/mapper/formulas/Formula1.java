package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;

import java.util.ArrayList;
import java.util.List;

public class Formula1 extends Formula {
    public Formula1() {
        
    }

    @Override
    public List<IndicatorResponse> getResultadoUsuario(List<OrdenesTrabajo> consult) {
        return getIndicadoresUsuario(consult);
    }

    @Override
    public List<IndicatorResponse> getResultadoSector(List<OrdenesTrabajo> consult) {
        return getIndicadoresSector(consult);
    }

    /**
     * Devuelve un indicador de sector
     * @param ordenes
     * @return indicadorResponse ya editado
     */
    IndicatorResponse getSector(List<OrdenesTrabajo> ordenes) {
        int ordenesTotales = getOrdenesTotalesUsuario(ordenes);
        int ordenesEnOk = getOrdenesEnOk(ordenes);
        int resultado = getFormula(ordenesEnOk, ordenesTotales);
        String nombre = "Sector: "+ordenes.get(0).getMaquina().getSector().getDescripcion();
        return setIndicador(new ArrayList<>(), nombre, resultado);
    }

    

    /**
     * Devuelve un indicador de usuario
     * @param ordenes
     * @return indicadorResponse ya editado
     */
   IndicatorResponse getUsuario(List<OrdenesTrabajo> ordenes){
        int ordenesTotales = getOrdenesTotalesUsuario(ordenes);
        int ordenesEnOk = getOrdenesEnOk(ordenes);
        int resultado = getFormula(ordenesEnOk, ordenesTotales);
        String nombreCompleto = ordenes.get(0).getResponsable().getApellido()+" "+ordenes.get(0).getResponsable().getNombre();
        return setIndicador(new ArrayList<>(), nombreCompleto, resultado);
   }

   

    /**
     * Formula 1, devuelve las ordenes en ok dividido las ordenes totales porcentaje
     * @param numerador cantidad de ordenes en ok
     * @param denominador cantidad de ordenes
     * @return
     */
   int getFormula(int numerador, int denominador){
        return (numerador*100)/denominador;
   }

    

    


}

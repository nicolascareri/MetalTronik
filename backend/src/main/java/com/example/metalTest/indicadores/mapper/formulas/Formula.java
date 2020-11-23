package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;

import java.util.ArrayList;
import java.util.List;

public abstract class Formula {
    int ok = 2;
    public abstract List<IndicatorResponse> getResultadoUsuario(List<OrdenesTrabajo> consult);

    public abstract  List<IndicatorResponse> getResultadoSector(List<OrdenesTrabajo> consult);

    abstract IndicatorResponse getUsuario(List<OrdenesTrabajo> ordenes);
    abstract IndicatorResponse getSector(List<OrdenesTrabajo> ordenes);
    abstract int getFormula(int a, int b);

    List<IndicatorResponse> getIndicadoresUsuario(List<OrdenesTrabajo> ordenes){
        List<OrdenesTrabajo> auxOrdenes= new ArrayList<>();
        List<IndicatorResponse> indicadores = new ArrayList<>();
        for (int i = 0; i < ordenes.size() ; i++) {
            auxOrdenes.add(ordenes.get(i));
            if (i+1<ordenes.size() && ordenes.get(i).getResponsable().getId() != ordenes.get(i+1).getResponsable().getId() ){
                indicadores.add(getUsuario(auxOrdenes));
                auxOrdenes.clear();
            }else{
                if (i+1== ordenes.size()){
                    indicadores.add(getUsuario(auxOrdenes));
                }
            }
        }
        return indicadores;
    }

    List<IndicatorResponse> getIndicadoresSector(List<OrdenesTrabajo> ordenes){
        List<OrdenesTrabajo> auxOrdenes= new ArrayList<>();
        List<IndicatorResponse> indicadores = new ArrayList<>();
        for (int i = 0; i < ordenes.size() ; i++) {
            auxOrdenes.add(ordenes.get(i));
            if (i+1<ordenes.size() && ordenes.get(i).getMaquina().getSector().getDescripcion() != ordenes.get(i+1).getMaquina().getSector().getDescripcion() ){
                indicadores.add(getSector(auxOrdenes));
                auxOrdenes.clear();
            }else{
                if (i+1== ordenes.size()){
                    indicadores.add(getSector(auxOrdenes));
                }
            }
        }
        return indicadores;
    }


    /**
     * Setea y devuelve un IndicadorResponse
     * @param data
     * @param nombreCompleto
     * @param resultado
     * @return
     */
    IndicatorResponse setIndicador(List<Integer> data, String nombreCompleto, int resultado){
        IndicatorResponse indicador = new IndicatorResponse();
        indicador.setLabel(nombreCompleto);
        data.add(resultado);
        indicador.setData(data);
        return indicador;
    }
    /**
     * devuelve la cantidad de elementos(ordenes) de un array
     * @param ordenes
     * @return
     */
    int getOrdenesTotalesUsuario(List<OrdenesTrabajo> ordenes){
        return ordenes.size();
    }

    /**
     * Devuelve la cantidad de ordenes en ok de un array
     * @param ordenes de trabajo de un usuario
     * @return cantidad en ok
     */
    int getOrdenesEnOk(List<OrdenesTrabajo> ordenes){
        int suma = 0;
        for (OrdenesTrabajo o : ordenes
        ) {
            if(o.getEstado() == this.ok){
                suma = suma +1;
            }
        }
        return suma;
    }
}

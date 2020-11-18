package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.indicadores.mapper.ToIndicadoresMapper;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;

import java.util.ArrayList;
import java.util.List;

public class Formula1 extends ToIndicadoresMapper implements Formula {
    private int ok;
    public Formula1() {
        ok = 2;
    }

    @Override
    public List<IndicatorResponse> getResultadoUsuario(List<OrdenesTrabajo> consult) {
        return this.getIndicadoresFormula1(consult);
    }

    @Override
    public List<IndicatorResponse> getResultadoSector(List<OrdenesTrabajo> consult) {
        return this.getIndicadoresFormula1Sector(consult);
    }

    private  List<IndicatorResponse> getIndicadoresFormula1Sector(List<OrdenesTrabajo> ordenes){
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

    private IndicatorResponse getSector(List<OrdenesTrabajo> ordenes) {
        int ordenesTotales = getOrdenesTotalesUsuario(ordenes);
        int ordenesEnOk = getOrdenesEnOk(ordenes);
        int resultado = getResultadoFormula(ordenesEnOk, ordenesTotales);
        String nombre = "Sector: "+ordenes.get(0).getMaquina().getSector().getDescripcion();
        return setIndicador(new ArrayList<>(), nombre, resultado);
    }

    private List<IndicatorResponse> getIndicadoresFormula1(List<OrdenesTrabajo> ordenes){
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

    /**
     * Devuelve un indicador de sector
     * @param ordenes
     * @return indicadorResponse ya editado
     */
   private IndicatorResponse getUsuario(List<OrdenesTrabajo> ordenes){
        int ordenesTotales = getOrdenesTotalesUsuario(ordenes);
        int ordenesEnOk = getOrdenesEnOk(ordenes);
        int resultado = getResultadoFormula(ordenesEnOk, ordenesTotales);
       String nombreCompleto = ordenes.get(0).getResponsable().getApellido()+" "+ordenes.get(0).getResponsable().getNombre();
        return setIndicador(new ArrayList<>(), nombreCompleto, resultado);
   }

    /**
     * Setea y devuelve un IndicadorResponse
     * @param data
     * @param nombreCompleto
     * @param resultado
     * @return
     */
   private IndicatorResponse setIndicador(List<Integer> data, String nombreCompleto, int resultado){
       IndicatorResponse indicador = new IndicatorResponse();
       indicador.setLabel(nombreCompleto);
       data.add(resultado);
       indicador.setData(data);
       return indicador;
   }

    /**
     * Formula 1, devuelve las ordenes en ok dividido las ordenes totales porcentaje
     * @param ok cantidad de ordenes en ok
     * @param totales cantidad de ordenes
     * @return
     */
   private int getResultadoFormula(int ok, int totales){
        return (ok *100)/totales;
   }

    /**
     * devuelve la cantidad de elementos(ordenes) de un array
     * @param ordenes
     * @return
     */
   private int getOrdenesTotalesUsuario(List<OrdenesTrabajo> ordenes){
       return ordenes.size();
   }

    /**
     * Devuelve la cantidad de ordenes en ok de un array
     * @param ordenes de trabajo de un usuario
     * @return cantidad en ok
     */
   private int getOrdenesEnOk(List<OrdenesTrabajo> ordenes){
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

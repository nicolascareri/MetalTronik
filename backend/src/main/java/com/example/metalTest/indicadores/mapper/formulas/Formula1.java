package com.example.metalTest.indicadores.mapper.formulas;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.indicadores.mapper.ToIndicadoresMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class Formula1 extends ToIndicadoresMapper implements Formula {

    public Formula1() {
    }

    @Override
    public List<IndicatorResponse> getResultado(List<String> consult) {
        return this.getIndicadoresFormula1(consult);
    }


    /**
     * Devuelve una lista con todos los indicadores
     * @param ordenes
     * @return
     */
    private List<IndicatorResponse> getIndicadoresFormula1(List<String> ordenes){
        List<IndicatorResponse> indicatorResponses = new ArrayList<>();
        for (String[] usuario: getOrdenesList(ordenes)) {
            IndicatorResponse indicador = this.getFormula1(usuario, new IndicatorResponse());
            indicatorResponses.add(indicador);
        }
        return indicatorResponses;
    }
    /**
     * Devuelve un indicador seteado segun los datos del parametro
     * parseando los datos del usuario Parametro
     * @param usuario
     * @return
     */
    private IndicatorResponse getFormula1(String[] usuario, IndicatorResponse indicador){
        indicador.setLabel(usuario[0]);
        Integer ok = toInt(usuario[1]);
        Integer pendiente = toInt(usuario[2]);
        //pregunto si pendiente es 0 porque tal vez no lo tenga
        //no se puede dividir por 0
        if(pendiente == 0){
            pendiente++;
        }
        Integer resultadoFormula = (ok /pendiente ) * 100;
        List<Integer> data = new ArrayList<>();
        data.add(resultadoFormula);
        indicador.setData(data);
        return indicador;
    }

    private  Integer toInt(String a){
        return Integer.parseInt(a);
    }

    /**
     * Parsea un string, devuelve un array de string
     * @param toParse String a parsear
     * @return array de strings parseados
     */
    private String[] parser(String toParse){
        return toParse.split(",");
    }

    /**
     * Parsea segun la cantidad que posea la posicion del arreglo de ordenes
     * @param ordenes lista de ordenes traida de la db
     * @return retorna en cada posicion de la lista hay un arreglo de strings
     */
    private List<String[]> getOrdenesList(List<String> ordenes){
        List<String[]> parseados = new ArrayList<>();
        for(String toParse: ordenes){
            parseados.add(this.parser(toParse));
        }
        return parseados;
    }



}

package com.example.metalTest.indicadores.mapper;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de convertir los datos traidos de la base a
 * datos trabajables
 */
public class ToIndicadoresMapper{

    public ToIndicadoresMapper() {
    }

    /**
     * Devuelve una lista con todos los indicadores
     * @param ordenes
     * @return
     */
    public List<IndicatorResponse> getIndicadores(List<String> ordenes){
        List<IndicatorResponse> indicatorResponses = new ArrayList<>();
        for (String[] usuario: getOrdenesList(ordenes)) {
            IndicatorResponse indicador = this.getIndicatorResponse(usuario, new IndicatorResponse());
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
    private IndicatorResponse getIndicatorResponse(String[] usuario, IndicatorResponse indicador){
        indicador.setLabel(usuario[0]);
        Integer ok =Integer.parseInt(usuario[1]);
        Integer pendiente = Integer.parseInt(usuario[2]);
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

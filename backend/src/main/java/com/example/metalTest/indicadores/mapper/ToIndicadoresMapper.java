package com.example.metalTest.indicadores.mapper;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.indicadores.controller.response.Torta;
import com.example.metalTest.indicadores.mapper.formulas.Formula;
import com.example.metalTest.indicadores.mapper.formulas.Formula1;
import com.example.metalTest.indicadores.mapper.formulas.Formula2;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de convertir los datos traidos de la base a
 * datos trabajables
 */

public class ToIndicadoresMapper  implements IndicadoresMapper {
    private Formula formula;

    public ToIndicadoresMapper() {
    }

    @Override
    public List<IndicatorResponse> toIndicadoresResponseFormula1(List<String[]> consult) {
        formula = new Formula1();
        return formula.getResultado(consult);
    }

    @Override
    public Torta toTorta(List<String[]> consult) {
        Torta torta = new Torta();
        List<String> labels = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (Object[] ob : consult){
            String b = String.valueOf(ob[0]);
            Integer c = Integer.valueOf((String)ob[1]);
            labels.add(b);
            numbers.add(c);
        }
        torta.setLabels(labels);
        torta.setNumber(numbers);
        return torta;
    }

}







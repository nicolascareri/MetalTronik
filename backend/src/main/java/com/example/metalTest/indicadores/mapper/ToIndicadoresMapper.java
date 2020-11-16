package com.example.metalTest.indicadores.mapper;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.indicadores.mapper.formulas.Formula;
import com.example.metalTest.indicadores.mapper.formulas.Formula1;
import com.example.metalTest.indicadores.mapper.formulas.Formula2;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import org.springframework.stereotype.Component;

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
    public List<IndicatorResponse> toIndicadoresResponseFormula1(List<String> consult) {
        formula = new Formula1();
        return formula.getResultado(consult);
    }

    @Override
    public List<IndicatorResponse> toIndicadoresResponseFormula2(List<String> consult) {
        formula = new Formula2();
        formula.getResultado(consult);
        return null;
    }
}







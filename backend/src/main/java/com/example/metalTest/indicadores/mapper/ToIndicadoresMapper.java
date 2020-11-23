package com.example.metalTest.indicadores.mapper;

import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.indicadores.mapper.formulas.Formula;
import com.example.metalTest.indicadores.mapper.formulas.Formula1;
import com.example.metalTest.indicadores.mapper.formulas.Formula2;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;

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
    public List<IndicatorResponse> toIndicadoresResponseFormula1Usuario(List<OrdenesTrabajo> consult) {
        formula = new Formula1();
        return formula.getResultadoUsuario(consult);
    }
    @Override
    public List<IndicatorResponse> toIndicadoresResponseFormula1Sector(List<OrdenesTrabajo> consult) {
        formula = new Formula1();
        return formula.getResultadoSector(consult);
    }

    @Override
    public List<IndicatorResponse> toIndicadoresResponseFormula2(List<OrdenesTrabajo> consult) {
        formula = new Formula2();
        return formula.getResultadoUsuario(consult);
    }
}







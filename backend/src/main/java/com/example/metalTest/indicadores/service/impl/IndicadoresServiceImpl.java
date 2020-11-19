package com.example.metalTest.indicadores.service.impl;

import com.example.metalTest.indicadores.mapper.IndicadoresMapper;
import com.example.metalTest.indicadores.mapper.ToIndicadoresMapper;
import com.example.metalTest.indicadores.service.IndicadoresService;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
import com.example.metalTest.mantenimientoCorrectivo.repository.MantenimientoCorrectivoRepository;
import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class IndicadoresServiceImpl implements IndicadoresService {
    @Autowired
    OrdenesTrabajoRepository ordenesTrabajoRepository;
    @Autowired
    MantenimientoCorrectivoRepository mantenimientoCorrectivoRepository;

    IndicadoresMapper toIndicadoresMapper = new ToIndicadoresMapper();


    //FORMULA 1
    @Override
    public List<IndicatorResponse> getIndicatorsForm1Usuario() {
        List<OrdenesTrabajo> ordenesList = this.ordenesTrabajoRepository.findAll();
        Collections.sort(ordenesList);
        return toIndicadoresMapper.toIndicadoresResponseFormula1Usuario(ordenesList);
    }

    @Override
    public List<IndicatorResponse> getIndicatorsForm1Sector() {
        List<OrdenesTrabajo> ordenesList = this.ordenesTrabajoRepository.getBySectorOrdenado();
        return toIndicadoresMapper.toIndicadoresResponseFormula1Sector(ordenesList);
    }

    //FORMULA 2
    @Override
    public List<IndicatorResponse> getIndicatorForm2Usuario(){
        List<OrdenesTrabajo> ordenesList = this.ordenesTrabajoRepository.findAll();
        Collections.sort(ordenesList);
        return toIndicadoresMapper.toIndicadoresResponseFormula1Usuario(ordenesList);
    }

    @Override
    public List<IndicatorResponse> getIndicatorForm2Sector() {
        return null;
    }


}

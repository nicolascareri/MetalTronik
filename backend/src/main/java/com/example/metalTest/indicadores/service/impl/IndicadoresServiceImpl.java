package com.example.metalTest.indicadores.service.impl;

import com.example.metalTest.indicadores.controller.response.Torta;
import com.example.metalTest.indicadores.mapper.IndicadoresMapper;
import com.example.metalTest.indicadores.mapper.ToIndicadoresMapper;
import com.example.metalTest.indicadores.service.IndicadoresService;
import com.example.metalTest.correctivo.repository.MantenimientoCorrectivoRepository;
import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.domain.OrdenesTrabajo;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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
        List<String[]> ordenesList = ordenesTrabajoRepository.getOrdenesFormula1Usuario();
        return toIndicadoresMapper.toIndicadoresResponseFormula1(ordenesList);
    }

    @Override
    public List<IndicatorResponse> getIndicatorsForm1Sector() {
        List<String[]> ordenesList = this.ordenesTrabajoRepository.getOrdenesFormula1Sector();
        return toIndicadoresMapper.toIndicadoresResponseFormula1(ordenesList);
    }

    //FORMULA 2
    @Override
    public List<IndicatorResponse> getIndicatorForm2Usuario(){
        List<OrdenesTrabajo> ordenesList = this.ordenesTrabajoRepository.findAll();
        Collections.sort(ordenesList);
        return null;
    }

    @Override
    public List<IndicatorResponse> getIndicatorForm2Sector() {
        return null;
    }

    @Override
    public Torta getGrafTortaTipo(){
        List<String[]>  a =  ordenesTrabajoRepository.getOrdenesTrabajoByTipo();
        return toIndicadoresMapper.toTorta(a);
    }
    @Override
    public Torta getGrafTortaPrioridad(){
        List<String[]>  a =  ordenesTrabajoRepository.getOrdenesTrabajoByPrioridad();
        return toIndicadoresMapper.toTorta(a);
    }

}

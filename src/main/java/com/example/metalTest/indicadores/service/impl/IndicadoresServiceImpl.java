package com.example.metalTest.indicadores.service.impl;

import com.example.metalTest.indicadores.controller.response.Torta;
import com.example.metalTest.indicadores.mapper.IndicadoresMapper;
import com.example.metalTest.indicadores.mapper.ToIndicadoresMapper;
import com.example.metalTest.indicadores.service.IndicadoresService;
import com.example.metalTest.mantenimientos.correctivo.repository.MantenimientoCorrectivoRepository;
import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
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
    public List<IndicatorResponse> getIndicatorsForm1Personal() {
        List<String[]> ordenesList = ordenesTrabajoRepository.getOrdenesFormula1Personal();
        return toIndicadoresMapper.toIndicadoresResponseFormula1(ordenesList);
    }

    @Override
    public List<IndicatorResponse> getIndicatorsForm1Sector() {
        List<String[]> ordenesList = this.ordenesTrabajoRepository.getOrdenesFormula1Sector();
        return toIndicadoresMapper.toIndicadoresResponseFormula1(ordenesList);
    }

    //FORMULA 2
    @Override
    public List<IndicatorResponse> getIndicatorForm2Personal(){
        List<String[]> ordenesList = this.ordenesTrabajoRepository.getOrdenesFormula2Personal();

        return toIndicadoresMapper.toIndicadoresResponseFormula2(ordenesList);
    }

    @Override
    public List<IndicatorResponse> getIndicatorForm2Sector() {
        List<String[]> ordenesList = this.ordenesTrabajoRepository.getOrdenesFormula2Sector();
        return toIndicadoresMapper.toIndicadoresResponseFormula2(ordenesList);
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

    @Override
    public List<IndicatorResponse> getLineChartMaquina(){
        List<String[]>  a =  mantenimientoCorrectivoRepository.getPromHorasMantenimientoMaquina();
        return toIndicadoresMapper.getLineChartMaquina(a);
    }

}

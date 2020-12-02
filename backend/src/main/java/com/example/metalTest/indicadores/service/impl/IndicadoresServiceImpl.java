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

    public Torta getGrafTorta(){
        List<String[]>  a =  ordenesTrabajoRepository.getOrdenesTrabajoByTipo();
        Torta torta = new Torta();
        List<String> labels = new ArrayList<>();
        List<Integer> numbers = new ArrayList<>();
        for (Object[] ob : a){
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

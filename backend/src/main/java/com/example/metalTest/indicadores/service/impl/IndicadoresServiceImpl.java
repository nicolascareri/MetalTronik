package com.example.metalTest.indicadores.service.impl;

import com.example.metalTest.common.ordenes.EstadoOrden;
import com.example.metalTest.indicadores.mapper.ToIndicadoresMapper;
import com.example.metalTest.indicadores.service.IndicadoresService;
import com.example.metalTest.mantenimientoCorrectivo.repository.MantenimientoCorrectivoRepository;
import com.example.metalTest.indicadores.controller.response.IndicatorResponse;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndicadoresServiceImpl implements IndicadoresService {
    @Autowired
    OrdenesTrabajoRepository ordenesTrabajoRepository;
    @Autowired
    MantenimientoCorrectivoRepository mantenimientoCorrectivoRepository;

    ToIndicadoresMapper toIndicadoresMapper = new ToIndicadoresMapper();


    //FORMULA 1
    @Override
    public List<IndicatorResponse> getIndicatorsForm1Usuario() {
        List<String> toParse = this.ordenesTrabajoRepository.getOrdenesTrabajoByUsuarios(EstadoOrden.OK.getValue(), EstadoOrden.PENDIENTE.getValue());
        return toIndicadoresMapper.getIndicadores(toParse);
    }

    @Override
    public List<IndicatorResponse> getIndicatorsForm1Sector() {
        List<String> toParse = this.ordenesTrabajoRepository.getOrdenesTrabajoBySectores(EstadoOrden.OK.getValue(),EstadoOrden.PENDIENTE.getValue());
        return toIndicadoresMapper.getIndicadores(toParse);
    }

    //FORMULA 2
    @Override
    public List<IndicatorResponse> getIndicatorForm2Usuario() {
        List<String> a = mantenimientoCorrectivoRepository.getManCor();
        System.out.println(a);
        return null;
    }

    @Override
    public List<IndicatorResponse> getIndicatorForm2Sector() {
        return null;
    }


}

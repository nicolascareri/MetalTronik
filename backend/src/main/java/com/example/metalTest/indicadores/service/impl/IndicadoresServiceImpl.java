package com.example.metalTest.indicadores.service.impl;

import com.example.metalTest.common.ordenes.EstadoOrden;
import com.example.metalTest.indicadores.mapper.IndicadoresMapper;
import com.example.metalTest.indicadores.mapper.ToIndicadoresMapper;
import com.example.metalTest.indicadores.service.IndicadoresService;
import com.example.metalTest.mantenimientoCorrectivo.domain.MantenimientoCorrectivo;
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

    IndicadoresMapper toIndicadoresMapper = new ToIndicadoresMapper();


    //FORMULA 1
    @Override
    public List<IndicatorResponse> getIndicatorsForm1Usuario() {
        List<String> toParse = this.ordenesTrabajoRepository.getOrdenesTrabajoByUsuarios(EstadoOrden.OK.getValue(), EstadoOrden.PENDIENTE.getValue());
        return toIndicadoresMapper.toIndicadoresResponseFormula1(toParse);
    }

    @Override
    public List<IndicatorResponse> getIndicatorsForm1Sector() {
        List<String> toParse = this.ordenesTrabajoRepository.getOrdenesTrabajoBySectores(EstadoOrden.OK.getValue(),EstadoOrden.PENDIENTE.getValue());
        return toIndicadoresMapper.toIndicadoresResponseFormula1(toParse);
    }

    //FORMULA 2
    @Override
    public List<IndicatorResponse> getIndicatorForm2Usuario(){
        List<String> consulta = mantenimientoCorrectivoRepository.getManCor();
        List<MantenimientoCorrectivo> a = mantenimientoCorrectivoRepository.findAll();
        System.out.println(a.get(0).nose());
        System.out.println("------------------>"+consulta);
        toIndicadoresMapper.toIndicadoresResponseFormula2(consulta);
        return null;
    }

    @Override
    public List<IndicatorResponse> getIndicatorForm2Sector() {
        return null;
    }


}

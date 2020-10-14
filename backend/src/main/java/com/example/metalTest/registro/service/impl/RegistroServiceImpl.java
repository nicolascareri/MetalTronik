package com.example.metalTest.registro.service.impl;

import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.registro.controller.response.RegistroResponse;
import com.example.metalTest.registro.service.RegistroService;
import com.example.metalTest.tarea.domain.Tarea;
import com.example.metalTest.tarea.mapper.TareaMapper;
import com.example.metalTest.tarea.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class RegistroServiceImpl implements RegistroService {
    @Autowired
    TareaRepository tareaRepository;

    @Autowired
    TareaMapper tareaMapper;

    @Override
    public List<RegistroResponse> getForMonth(Date date) {
        List<RegistroResponse> registroResponseList = new ArrayList<>();
        List<Tarea> tareaList = tareaRepository.findAll();
        Calendar fechaPlanificada = Calendar.getInstance();
        fechaPlanificada.setTime(date);
        tareaList.forEach(tarea -> {
            if (tareaValida(tarea, fechaPlanificada)){
                Calendar fechaInicio = Calendar.getInstance();
                fechaInicio.setTime(tarea.getInicio());
                fechaInicio = calcularFechaInicio(fechaPlanificada, fechaInicio, tarea.getFrecuencia());
                while (fechaInicio.get(Calendar.MONTH) == fechaPlanificada.get(Calendar.MONTH) ){
                    RegistroResponse registro = new RegistroResponse();
                    registro.setTarea(tareaMapper.toTareaResponse(tarea));
                    registro.setFechaPlanificada(fechaInicio.getTime());
                    registroResponseList.add(registro);
                    fechaInicio.add(Calendar.DAY_OF_YEAR, tarea.getFrecuencia());
                }
            }
        });
        return registroResponseList;
    }

    private Boolean tareaValida(Tarea tarea, Calendar fechaPlanificada){
        Calendar fechaInicio = Calendar.getInstance();
        fechaInicio.setTime(tarea.getInicio());
        return tarea.getEstado().equals(Estado.ACTIVO.getValue()) && fechaInicio.get(Calendar.MONTH) <= fechaPlanificada.get(Calendar.MONTH);
    }

    private Calendar calcularFechaInicio(Calendar fechaPlanificada, Calendar fechaInicio, int frecuencia){
        while(!fechaValida(fechaPlanificada, fechaInicio)){
            fechaInicio.add(Calendar.DAY_OF_YEAR, frecuencia);
        }
        return fechaInicio;
    }

    private Boolean fechaValida(Calendar fechaPlanificada, Calendar fechaInicio){
        return ( fechaPlanificada.get(Calendar.YEAR) == fechaInicio.get(Calendar.YEAR) ) &&
                ( fechaPlanificada.get(Calendar.MONTH) == fechaInicio.get(Calendar.MONTH) );
    }
}

package com.example.metalTest.registro.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.registro.controller.request.RegistroRequest;
import com.example.metalTest.registro.domain.Registro;
import com.example.metalTest.registro.repository.RegistroRepository;
import com.example.metalTest.registro.service.RegistroService;
import com.example.metalTest.tarea.domain.Tarea;
import com.example.metalTest.tarea.mapper.TareaMapper;
import com.example.metalTest.tarea.repository.TareaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegistroServiceImpl implements RegistroService {
    @Autowired
    TareaRepository tareaRepository;

    @Autowired
    RegistroRepository registroRepository;

    @Autowired
    TareaMapper tareaMapper;

    @Override
    public List<Registro> getForMonth(Date date) {
        List<Registro> registroList = new ArrayList<>();
        List<Tarea> tareaList = tareaRepository.findAll();
        Calendar fechaPlanificada = Calendar.getInstance();
        fechaPlanificada.setTime(date);
        tareaList.forEach(tarea -> {
            if (tareaValida(tarea, fechaPlanificada)){
                Calendar fechaInicio = Calendar.getInstance();
                fechaInicio.setTime(tarea.getInicio());
                fechaInicio = calcularFechaInicio(fechaPlanificada, fechaInicio, tarea.getFrecuencia());
                while (fechaInicio.get(Calendar.MONTH) == fechaPlanificada.get(Calendar.MONTH) ){
                    Registro registro = new Registro();
                    registro.setTarea(tarea);
                    registro.setFechaPlanificada(fechaInicio.getTime());
                    registroList.add(registro);
                    fechaInicio.add(Calendar.DAY_OF_YEAR, tarea.getFrecuencia());
                }
            }
        });
        return registroList;
    }

    @Override
    public List<Registro> savePlanificacionActual() throws ValidateFieldException {
        Date fechaActual = new Date(System.currentTimeMillis());
        if(registroRepository.existsPlanificacionActual()){
            throw new ValidateFieldException("Ya existe una planificacion para este mes","mes",String.valueOf(Calendar.getInstance().get(Calendar.MONTH) + 1));
        }
        return registroRepository.saveAll(getForMonth(fechaActual));
    }

    @Override
    public Registro update(RegistroRequest registroRequest, Integer id) throws ValidateFieldException {
        Optional<Registro> optionalRegistro = registroRepository.findById(id);
        if (!optionalRegistro.isPresent()){
            throw new ValidateFieldException("El registro que desea acceder no existe","id", String.valueOf(id));
        }
        Registro registro = optionalRegistro.get();
        registro.setFechaPlanificada(registroRequest.getFechaPlanificada());
        registro.setObservaciones(registroRequest.getObservaciones());
        registro.setRealizo(registroRequest.getRealizo());
        registro.setTarea(tareaRepository.getOne(registroRequest.getTarea_cod()));
        return registroRepository.save(registro);
    }

    @Override
    public List<Registro> getActualOrPast(Date date) {
        Calendar fecha = Calendar.getInstance();
        fecha.setTime(date);
        return registroRepository.getByDate(fecha.get(Calendar.MONTH) + 1, fecha.get(Calendar.YEAR));
    }

    @Override
    public Registro getById(Integer id) throws ValidateFieldException {
        Optional<Registro> optionalRegistro = registroRepository.findById(id);
        if (!optionalRegistro.isPresent()){
            throw new ValidateFieldException("El registro que desea acceder no existe", "id", String.valueOf(id));
        }
        return optionalRegistro.get();
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

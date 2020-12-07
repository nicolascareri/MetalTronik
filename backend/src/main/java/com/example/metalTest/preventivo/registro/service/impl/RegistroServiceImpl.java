
package com.example.metalTest.preventivo.registro.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.common.ordenes.Estado;
import com.example.metalTest.preventivo.registro.controller.request.RegistroRequest;
import com.example.metalTest.preventivo.registro.domain.Registro;
import com.example.metalTest.preventivo.registro.repository.RegistroRepository;
import com.example.metalTest.preventivo.registro.service.RegistroService;
import com.example.metalTest.preventivo.tarea.tareas.domain.Tareas;
import com.example.metalTest.preventivo.tarea.tareas.mapper.TareasMapper;
import com.example.metalTest.preventivo.tarea.tareas.repository.TareasRepository;
import com.example.metalTest.usuario.domain.Usuario;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RegistroServiceImpl implements RegistroService {
    @Autowired
    TareasRepository tareasRepository;

    @Autowired
    RegistroRepository registroRepository;
    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    TareasMapper tareasMapper;

    @Override
    public List<Registro> getForMonth(Date date) {
        List<Registro> registroList = new ArrayList<>();
        //busca todas las tareas
        List<Tareas> tareaList = tareasRepository.findAll();
        Calendar fechaPlanificada = Calendar.getInstance();
        //setea la fecha planificada con el parametro
        fechaPlanificada.setTime(date);
        //recorre cada tareas
        tareaList.forEach(tarea -> {
            if (tareaValida(tarea, fechaPlanificada)){
                Calendar fechaInicio = Calendar.getInstance();
                //setea la fecha de inicio de la tareas actual
                fechaInicio.setTime(tarea.getInicio());
                //Agrega dias al calendario segun la frecuencia hasta llegar a la fecha actual
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
        List<Registro> a = new ArrayList<>();
        a.addAll(getForMonth(fechaActual));
        return registroRepository.saveAll(a);
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
        registro.setTarea(tareasRepository.getOne(registroRequest.getTarea_cod()));
        registro.setEncargado(usuarioRepository.findById(registroRequest.getEncargado()).get());
        registro.setFechaRealizada(registroRequest.getFechaRealizada());
        return registroRepository.save(registro);
    }

    @Override
    public List<Registro> getActualOrPast(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        return registroRepository.getByDate(year ,month);
    }

    @Override
    public Registro getById(Integer id) throws ValidateFieldException {
        Optional<Registro> optionalRegistro = registroRepository.findById(id);
        if (!optionalRegistro.isPresent()){
            throw new ValidateFieldException("El registro que desea acceder no existe", "id", String.valueOf(id));
        }
        return optionalRegistro.get();
    }


    private Boolean tareaValida(Tareas tarea, Calendar fechaPlanificada){
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
package com.example.metalTest.preventivo.tarea.historial.service.TareaHistServiceImpl;

import com.example.metalTest.common.validator.RepositoryValidator;
import com.example.metalTest.parte.domain.Parte;
import com.example.metalTest.preventivo.tarea.historial.domain.TareaHistorial;
import com.example.metalTest.preventivo.tarea.historial.repository.TareaHistorialRepository;
import com.example.metalTest.preventivo.tarea.historial.service.TareaHistorialService;
import com.example.metalTest.preventivo.tarea.tareas.domain.Tareas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class TareaHistServiceImpl implements TareaHistorialService {

    @Autowired
    TareaHistorialRepository tareaHistorialRepository;

    @Autowired
    RepositoryValidator repositoryValidator;

    @Override
    public List<TareaHistorial> getById(Integer id) {
        return  tareaHistorialRepository.findByTarea(id);
    }

    @Override
    public TareaHistorial create(Tareas tareas) {
        TareaHistorial newTareaHistorial = new TareaHistorial();
        newTareaHistorial.setInicio(tareas.getInicio());
        newTareaHistorial.setFrecuencia(tareas.getFrecuencia());
        newTareaHistorial.setMaquina(tareas.getMaquina().getDescripcion());
        Parte a = tareas.getParte();
        if(a != null){
            newTareaHistorial.setParte(tareas.getParte().getNombre());
        }
        newTareaHistorial.setTarea_id(tareas.getId());
        newTareaHistorial.setTarea(tareas.getTarea());
        Date date = new Date();
        newTareaHistorial.setFecha_cambio(date);
        return tareaHistorialRepository.save(newTareaHistorial);
    }
}

package com.example.metalTest.mantenimientos.preventivo.tarea.historial.service;

import com.example.metalTest.mantenimientos.preventivo.tarea.historial.domain.TareaHistorial;
import com.example.metalTest.mantenimientos.preventivo.tarea.tareas.domain.Tareas;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TareaHistorialService {

    List<TareaHistorial> getById(Integer id);

    TareaHistorial create(Tareas tareas);
}

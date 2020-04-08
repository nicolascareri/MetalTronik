package com.example.metalTest.tarea.service;

import com.example.metalTest.tarea.controller.request.TareaRequest;
import com.example.metalTest.tarea.domain.Tarea;

import java.util.List;

public interface TareaService {

    List<Tarea> getAll();

    Tarea create(TareaRequest tareaRequest);

    List<Tarea> getByUsuario(Integer id);
}

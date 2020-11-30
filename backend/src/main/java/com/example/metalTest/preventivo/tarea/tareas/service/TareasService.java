package com.example.metalTest.preventivo.tarea.tareas.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.preventivo.tarea.tareas.controller.Response.TareasResponse;
import com.example.metalTest.preventivo.tarea.tareas.controller.request.TareasRequest;

import java.util.List;

public interface TareasService {
    List<TareasResponse> getAll();

    TareasResponse getById(Integer id) throws ValidateFieldException;

    TareasResponse update(Integer id, TareasRequest tareasRequest) throws ValidateFieldException;

    TareasResponse create(TareasRequest tareasRequest) throws ValidateFieldException;
}

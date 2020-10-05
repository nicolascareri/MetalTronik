package com.example.metalTest.tarea.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.tarea.controller.Response.TareaResponse;
import com.example.metalTest.tarea.controller.request.TareaRequest;
import com.example.metalTest.tarea.domain.Tarea;

import java.util.List;

public interface TareaService {
    List<TareaResponse> getAll();

    TareaResponse getById(Integer id) throws ValidateFieldException;

    TareaResponse update(Integer id, TareaRequest tareaRequest) throws ValidateFieldException;

    TareaResponse create(TareaRequest tareaRequest) throws ValidateFieldException;
}

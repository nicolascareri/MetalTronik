package com.example.metalTest.prioridades.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.prioridades.domain.Prioridades;

import java.util.List;

public interface PrioridadesService {

    List<Prioridades> getAll();

    Prioridades getById(Integer id) throws ValidateFieldException;

    Prioridades create(Prioridades prioridades) throws ValidateFieldException;

    Prioridades update(Prioridades prioridadesRequestToPrioridades, Integer id) throws ValidateFieldException;
}

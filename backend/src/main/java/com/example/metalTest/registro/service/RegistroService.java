package com.example.metalTest.registro.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.registro.controller.request.RegistroRequest;
import com.example.metalTest.registro.domain.Registro;

import java.util.Date;
import java.util.List;

public interface RegistroService {
    List<Registro> getForMonth(Date date);

    List<Registro> savePlanificacionActual() throws ValidateFieldException;

    Registro update(RegistroRequest registroRequest, Integer id) throws ValidateFieldException;

    List<Registro> getActualOrPast(Date date);

    Registro getById(Integer id) throws ValidateFieldException;
}

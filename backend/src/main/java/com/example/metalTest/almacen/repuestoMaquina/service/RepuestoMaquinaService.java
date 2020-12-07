package com.example.metalTest.almacen.repuestoMaquina.service;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.almacen.repuestoMaquina.controller.request.RepuestoMaquinaRequest;
import com.example.metalTest.almacen.repuestoMaquina.controller.response.RepuestoMaquinaResponse;

import java.util.List;

public interface RepuestoMaquinaService {
    List<RepuestoMaquinaResponse> vincular(List<RepuestoMaquinaRequest> repuestoMaquinaRequestList, Integer id) throws ValidateFieldException;

    List<RepuestoMaquinaResponse> getAll();
}

package com.example.metalTest.almacen.asociacion.service;

import com.example.metalTest.almacen.asociacion.controller.request.AsociarList;
import com.example.metalTest.almacen.asociacion.controller.response.AsociacionResponse;
import com.example.metalTest.almacen.asociacion.domain.Asociacion;
import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.ordenestrabajo.controller.response.OrdenesTrabajoResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface AsosiacionService {
    void asociar(AsociarList asociarList) throws ValidateFieldException;

    List<Asociacion> getVinculados();

    List<Maquina> getSinVincular();

    AsociacionResponse getById(Integer id) throws ValidateFieldException;

    AsociacionResponse update(AsociarList asociarList, Integer id) throws ValidateFieldException;
}

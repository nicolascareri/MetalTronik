package com.example.metalTest.almacen.asociacion.service;

import com.example.metalTest.almacen.asociacion.controller.request.AsociarList;
import com.example.metalTest.almacen.asociacion.domain.Asociacion;
import com.example.metalTest.maquina.domain.Maquina;

import java.util.List;

public interface AsosiacionService {
    void asociar(AsociarList asociarList);

    List<Asociacion> getVinculados();

    List<Maquina> getSinVincular();
}

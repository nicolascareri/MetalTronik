package com.example.metalTest.almacen.repuesto.controller.response;

import com.example.metalTest.maquina.domain.Maquina;
import com.example.metalTest.parte.domain.Parte;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsociacionResponse {
    private int id;
    private String nombre;
    private String modelo;
    private Integer cantidad_instalada;
    private Maquina maquina;
    private Parte parte;
}

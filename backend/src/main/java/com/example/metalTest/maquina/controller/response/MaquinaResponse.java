package com.example.metalTest.maquina.controller.response;

import com.example.metalTest.common.estado.Estado;
import com.example.metalTest.tipo.domain.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaquinaResponse {
    private int id;
    private String maquina_cod;
    private Estado estado;
    private Tipo planta;
    private Tipo sector;
    private String equipo;
    private String descripcion;
}

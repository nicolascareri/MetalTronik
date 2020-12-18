package com.example.metalTest.maquina.controller.response;

import com.example.metalTest.tipo.domain.Tipo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MaquinaReducidoResponse {
    private int id;
    private String maquina_cod;
    private short estado;
    private Tipo planta;
    private Tipo sector;
    private String equipo;
    private String descripcion;
}

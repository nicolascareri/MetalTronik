package com.example.metalTest.maquina.controller.response;

import com.example.metalTest.parte.domain.Parte;
import com.example.metalTest.planta.domain.Planta;
import com.example.metalTest.sector.domain.Sector;
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
    private Planta planta;
    private Sector sector;
    private String equipo;
    private String descripcion;
}

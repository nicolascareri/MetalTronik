package com.example.metalTest.maquina.controller.response;

import com.example.metalTest.planta.domain.Planta;
import com.example.metalTest.almacen.repuestoMaquina.controller.response.RepuestoMaquinaResponse;
import com.example.metalTest.sector.domain.Sector;
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
    private short estado;
    private Planta planta;
    private Sector sector;
    private String equipo;
    private String modelo;
    private String descripcion;
    private List<RepuestoMaquinaResponse> repuestoMaquinaList = new ArrayList<>();
}

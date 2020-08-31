package com.example.metalTest.maquina.controller.response;

import com.example.metalTest.planta.domain.Planta;
import com.example.metalTest.repuestoMaquina.controller.response.RepuestoMaquinaResponse;
import com.example.metalTest.repuestoMaquina.domain.RepuestoMaquina;
import com.example.metalTest.sector.domain.Sector;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class MaquinaResponse {

    private int id;
    private String maquina_cod;
    private short estado;
    private Planta planta;
    private Sector sector;
    private String equipo;
    private int nro_serie;
    private String modelo;
    private String descripcion;
    private String datos_tecnicos;
    private List<RepuestoMaquinaResponse> repuestoMaquinaList = new ArrayList<>();
}

package com.example.metalTest.ordenestrabajo.controller.request;

import com.example.metalTest.common.util.JsonDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrdenesTrabajoRequest {
    private String planta;
    private String maquina_cod;
    private String pedidoMateriales;
    private int tarea_cod;
    private String priodidad;
    private String tipo;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    @JsonDeserialize(using = JsonDateDeserializer.class)
    private Date fechaRealizar;
    private int encargo_cod;
    private int responsable_cod;
    private String estado;
}

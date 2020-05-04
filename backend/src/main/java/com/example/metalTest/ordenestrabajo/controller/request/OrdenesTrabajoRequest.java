package com.example.metalTest.ordenestrabajo.controller.request;

import com.example.metalTest.common.util.JsonDateDeserializer;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class OrdenesTrabajoRequest {

    @NotNull
    @Min(1)
    @Max(2)
    private short planta;
    @NotNull
    private String maquina_cod;
    @NotNull
    private String pedidoMateriales;
    @NotNull
    private String tarea;
    @NotNull
    @Min(1)
    @Max(7)
    private short priodidad;
    @NotNull
    @Min(1)
    @Max(3)
    private short tipo;
    @NotNull
    private Date fechaRealizar;
    @NotNull
    private int encargo_cod;
    @NotNull
    private int responsable_cod;
    @NotNull
    @Min(1)
    @Max(2)
    private short estado;
    @NotNull
    private int sector_cod;
}

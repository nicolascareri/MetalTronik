package com.example.metalTest.ordenestrabajo.controller.response;

import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.parte.controller.response.ParteParaOrdenResponse;
import com.example.metalTest.prioridades.domain.Prioridades;
import com.example.metalTest.tipo.domain.Tipo;
import com.example.metalTest.usuario.domain.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class OrdenesTrabajoResponse {

    private int ordentrabajo_cod;
    private ParteParaOrdenResponse parte;
    private MaquinaReducidoResponse maquina;
    private String pedidoMateriales;
    private String tarea;
    private Prioridades prioridad;
    private Tipo tipo;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fechaEntrega;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fechaRealizar;
    private Usuario encargo;
    private Usuario responsable;
    private short estado;
    private String observaciones;
    private int ordenTerciarizacion;
}

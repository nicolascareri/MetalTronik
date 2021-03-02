package com.example.metalTest.mantenimientos.preventivo.registro.controller.response;

import com.example.metalTest.mantenimientos.preventivo.tarea.tareas.controller.Response.TareasResponse;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegistroResponseParaListado {

    private Integer id;
    private TareasResponse tarea;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fechaPlanificada;
    private Boolean realizo;
    private String observaciones;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date fechaRealizada;
    private Personal encargado;

}

package com.example.metalTest.preventivo.registro.controller.response;

import com.example.metalTest.preventivo.tarea.tareas.controller.Response.TareasResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegistroResponse {
    TareasResponse tarea;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    Date fechaPlanificada;
}

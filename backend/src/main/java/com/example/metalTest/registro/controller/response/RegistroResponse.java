package com.example.metalTest.registro.controller.response;

import com.example.metalTest.tarea.controller.Response.TareaResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegistroResponse {
    TareaResponse tarea;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    Date fechaPlanificada;
}

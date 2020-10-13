package com.example.metalTest.registro.controller.response;

import com.example.metalTest.tarea.controller.Response.TareaResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RegistroResponse {
    TareaResponse tarea;
    Date fechaPlanificada;
}

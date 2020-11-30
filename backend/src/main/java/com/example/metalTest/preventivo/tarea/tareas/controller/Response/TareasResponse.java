package com.example.metalTest.preventivo.tarea.tareas.controller.Response;

import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.parte.controller.response.ParteParaOrdenResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class TareasResponse {
    private int id;
    private String tarea;
    private MaquinaReducidoResponse maquina;
    private ParteParaOrdenResponse parte;
    private int frecuencia;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date inicio;
    private Short estado;
}

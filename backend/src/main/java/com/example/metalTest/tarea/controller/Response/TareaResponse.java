package com.example.metalTest.tarea.controller.Response;

import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.maquina.controller.response.MaquinaResponse;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class TareaResponse {
    private int id;
    private String tarea;
    private MaquinaReducidoResponse maquina;
    private int frecuencia;
    private Date inicio;
    private Short estado;
}

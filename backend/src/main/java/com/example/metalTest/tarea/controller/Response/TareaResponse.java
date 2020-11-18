package com.example.metalTest.tarea.controller.Response;

import com.example.metalTest.maquina.controller.response.MaquinaReducidoResponse;
import com.example.metalTest.tareaTipo.controller.response.TareaTipoResponse;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class TareaResponse {
    private int id;
    private TareaTipoResponse tarea;
    private MaquinaReducidoResponse maquina;
    private int frecuencia;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT-3")
    private Date inicio;
    private Short estado;
}

package com.example.metalTest.preventivo.tarea.tareas.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class TareasRequest {
    private String tarea;
    @ValidEntity(repository = MaquinaRepository.class)
    private Integer maquina_id;
    private Integer parte_id;
    private int frecuencia;
    private Date inicio;
}

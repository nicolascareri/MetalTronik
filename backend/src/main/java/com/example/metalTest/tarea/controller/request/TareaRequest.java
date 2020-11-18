package com.example.metalTest.tarea.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.tareaTipo.domain.TareaTipo;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class TareaRequest {
    private int tareaTipoId;
    @ValidEntity(repository = MaquinaRepository.class)
    private Integer maquina_cod;
    private int frecuencia;
    private Date inicio;
    private Short estado;
}

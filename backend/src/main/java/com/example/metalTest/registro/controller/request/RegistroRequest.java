package com.example.metalTest.registro.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.tarea.repository.TareaRepository;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class RegistroRequest {

    @ValidEntity(repository = TareaRepository.class)
    private Integer tarea_cod;
    private Date fechaPlanificada;
    private Boolean realizo;
    private String observaciones;
    private Date fechaRealizada;
}

package com.example.metalTest.preventivo.registro.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.preventivo.tarea.tareas.repository.TareasRepository;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class RegistroRequest {

    @ValidEntity(repository = TareasRepository.class)
    private Integer tarea_cod;
    private Date fechaPlanificada;
    private Boolean realizo;
    private String observaciones;
    @ValidEntity(repository = UsuarioRepository.class)
    private Integer encargado;
    private Date fechaRealizada;
}

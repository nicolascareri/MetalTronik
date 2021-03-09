package com.example.metalTest.ordenestrabajo.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuarios.personal.repository.PersonalRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class OrdenesTrabajoRequest {

    @NotNull
    @ValidEntity(repository = MaquinaRepository.class)
    private int maquina_id;

    private Integer parte_id;
    @NotNull
    private String pedidoMateriales;
    @NotNull
    private String tarea;
    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private Integer prioridad_id;
    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private Integer tipo_id;
    @NotNull
    private Date fechaRealizar;
    @NotNull
    private Date fechaEntrega;
    @NotNull
    @ValidEntity(repository = PersonalRepository.class)
    private int encargo_id;
    @NotNull
    @ValidEntity(repository = PersonalRepository.class)
    private int responsable_id;

    private String observaciones;

    private int ordenTerciarizacion;

    private short estado;
}

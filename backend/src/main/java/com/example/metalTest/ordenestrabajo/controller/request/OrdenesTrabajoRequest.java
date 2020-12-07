package com.example.metalTest.ordenestrabajo.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.prioridades.repository.PrioridadesRepository;
import com.example.metalTest.tipo.repository.TipoRepository;
import com.example.metalTest.usuario.repository.UsuarioRepository;
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
    @ValidEntity(repository = PrioridadesRepository.class)
    private int prioridad_cod;
    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private int tipo_cod;
    @NotNull
    private Date fechaRealizar;
    @NotNull
    private Date fechaEntrega;
    @NotNull
    @ValidEntity(repository = UsuarioRepository.class)
    private int encargo_cod;
    @NotNull
    @ValidEntity(repository = UsuarioRepository.class)
    private int responsable_cod;

    private String observaciones;

    private int ordenTerciarizacion;

    private short estado;
}

package com.example.metalTest.mantenimientoCorrectivo.controller.request;

import com.example.metalTest.common.validator.ValidEntity;
import com.example.metalTest.maquina.repository.MaquinaRepository;
import com.example.metalTest.ordenestrabajo.repository.OrdenesTrabajoRepository;
import com.example.metalTest.sector.repository.SectorRepository;
import com.example.metalTest.usuario.repository.UsuarioRepository;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
public class MantenimientoCorrectivoRequest {

    @NotNull
    @ValidEntity(repository = MaquinaRepository.class)
    private int maquina_cod;

    @NotNull
    private Date fechainicio;

    @NotNull
    private Date fechaFin;

    @NotNull
    private int tipofalla;

    @NotNull
    private int horasProduccionAfectadas;

    private String observaciones;

    @ValidEntity(repository = OrdenesTrabajoRepository.class)
    private int ordenTrabajo_cod;

    @NotNull
    private int nrocorrectivo;

    @NotNull
    @ValidEntity(repository = UsuarioRepository.class)
    private int encargo1_cod;

    @ValidEntity(repository = UsuarioRepository.class)
    private int encargo2_cod;

    @ValidEntity(repository = UsuarioRepository.class)
    private int encargo3_cod;
}

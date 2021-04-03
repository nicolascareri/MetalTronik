package com.example.metalTest.mantenimientos.correctivo.controller.request;

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
public class MantenimientoCorrectivoRequest {

    @NotNull
    @ValidEntity(repository = MaquinaRepository.class)
    private int maquina_id;

    private Integer parte_id;

    @NotNull
    private Date fechainicio;

    @NotNull
    private Date fechaFin;

    @NotNull
    @ValidEntity(repository = TipoRepository.class)
    private int tipo_id;

    @NotNull
    private int horasProduccionAfectadas;

    private String observaciones;

    private String repuestosColocados;

    private Integer ordenTrabajo_id;

    @NotNull
    private int nrocorrectivo;

    @NotNull
    @ValidEntity(repository = PersonalRepository.class)
    private int encargo1_id;

    private int encargo2_id;

    private int encargo3_id;
}
